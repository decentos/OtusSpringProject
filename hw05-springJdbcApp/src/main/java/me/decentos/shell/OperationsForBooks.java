package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.dao.BookDao;
import me.decentos.model.Book;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class OperationsForBooks {

    private final BookDao bookDao;

    @ShellMethod(value = "Get count of books", key = {"booksCount"})
    public int getCount() {
        return bookDao.count();
    }

    @ShellMethod(value = "Insert book", key = {"insertBook"})
    public String insert(String title, long authorId, long genreId) {
        Book book = new Book(title, authorId, genreId);
        bookDao.insert(book);
        return String.format("Вы добавили новую книгу: %s", book.getTitle());
    }

    @ShellMethod(value = "Get book", key = {"getBook"})
    public String getById(long id) {
        Book book = bookDao.getById(id);
        return book.getTitle();
    }

    @ShellMethod(value = "Get all books", key = {"getAllBooks"})
    public void getAll() {
        List<Book> list = bookDao.getAll();
        list.forEach(book -> System.out.println(book.getTitle()));
    }

    @ShellMethod(value = "Delete book", key = {"deleteBook"})
    public String deleteById(long id) {
        Book book = bookDao.getById(id);
        bookDao.deleteById(id);
        return String.format("Вы удалили автора: %s", book.getTitle());
    }
}
