package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Author;
import me.decentos.repositories.AuthorRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommandsForAuthors {

    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Get count of authors", key = {"authorsCount"})
    public long getCount() {
        return authorRepository.count();
    }

    @ShellMethod(value = "Save author", key = {"saveAuthor"})
    public String save(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorRepository.save(author);
        return String.format("Вы добавили нового автора: %s %s", author.getFirstName(), author.getLastName());
    }

    @ShellMethod(value = "Find author", key = {"findAuthor"})
    public String findById(long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return String.format("%s %s", author.getFirstName(), author.getLastName());
    }

    @ShellMethod(value = "Find all authors", key = {"findAllAuthors"})
    public void getAll() {
        List<Author> list = authorRepository.findAll();
        list.forEach(author -> System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName())));
    }

    @ShellMethod(value = "Update authors", key = {"updateAuthor"})
    public String update(long id, String firstName, String lastName) {
        Author oldAuthor = authorRepository.findById(id).orElseThrow();
        String oldFirstName = oldAuthor.getFirstName();
        String oldLastName = oldAuthor.getLastName();

        authorRepository.save(new Author(id, firstName, lastName));

        Author updateAuthor = authorRepository.findById(id).orElseThrow();
        String updateFirstName = updateAuthor.getFirstName();
        String updateLastName = updateAuthor.getLastName();

        return String.format("Вы успешло изменили автора с %s %s на %s %s", oldFirstName, oldLastName, updateFirstName, updateLastName);
    }

    @ShellMethod(value = "Delete author", key = {"deleteAuthor"})
    public String deleteById(long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.deleteById(id);
        return String.format("Вы удалили автора: %s %s", author.getFirstName(), author.getLastName());
    }
}
