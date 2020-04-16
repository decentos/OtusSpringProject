package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import me.decentos.repositories.BookRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommandsForBooks {

    private final BookRepository bookRepository;

    @ShellMethod(value = "Get count of books", key = {"booksCount"})
    public long getCount() {
        return bookRepository.count();
    }

    @ShellMethod(value = "Save book", key = {"saveBook"})
    public String save(String title, String firstName, String lastName, String genreName) {
        Author author = new Author(firstName, lastName);
        Genre genre = new Genre(genreName);
        Book book = new Book(title, author, genre);
        bookRepository.save(book);
        return String.format("Вы добавили новую книгу:\nНазвание: %s\nАвтор: %s %s\nЖанр: %s",
                book.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                genre.getGenre()
        );
    }

    @ShellMethod(value = "Find book", key = {"findBook"})
    public String getById(long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return String.format("Название: %s\nАвтор: %s %s\nЖанр: %s",
                book.getTitle(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(),
                book.getGenre().getGenre()
        );
    }

    @ShellMethod(value = "Find all books", key = {"findAllBooks"})
    public void getAll() {
        List<Book> list = bookRepository.findAll();
        list.forEach(book -> System.out.println(
                String.format("Название: %s\nАвтор: %s %s\nЖанр: %s\n",
                        book.getTitle(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),
                        book.getGenre().getGenre()
                )
        ));
    }

    @ShellMethod(value = "Update book", key = {"updateBook"})
    public String update(long id, String title, String firstName, String lastName, String genreName) {
        Book oldBook = bookRepository.findById(id).orElseThrow();
        String oldTitle = oldBook.getTitle();
        String oldAuthor = oldBook.getAuthor().getFirstName() + " " + oldBook.getAuthor().getLastName();
        String oldGenre = oldBook.getGenre().getGenre();

        Author author = new Author(firstName, lastName);
        Genre genre = new Genre(genreName);
        Book book = new Book(id, title, author, genre);

        bookRepository.save(book);

        Book updateBook = bookRepository.findById(id).orElseThrow();
        String updateTitle = updateBook.getTitle();
        String updateAuthor = updateBook.getAuthor().getFirstName() + " " + updateBook.getAuthor().getLastName();
        String updateGenre = updateBook.getGenre().getGenre();

        StringBuilder sb = new StringBuilder("Вы успешло изменили: \n");
        if (!oldTitle.equals(updateTitle)) sb.append("- название книгу с ").append(oldTitle).append(" на ").append(updateTitle).append("\n");
        if (!oldAuthor.equals(updateAuthor)) sb.append("- автора с ").append(oldAuthor).append(" на ").append(updateAuthor).append("\n");
        if (!oldGenre.equals(updateGenre)) sb.append("- жанр с ").append(oldGenre).append(" на ").append(updateGenre);

        return sb.toString();
    }

    @ShellMethod(value = "Delete book", key = {"deleteBook"})
    public String deleteById(long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.deleteById(id);
        return String.format("Вы удалили книгу: %s", book.getTitle());
    }
}
