package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.dao.AuthorDao;
import me.decentos.model.Author;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class OperationsForAuthors {

    private final AuthorDao authorDao;

    @ShellMethod(value = "Get count of authors", key = {"authorsCount"})
    public int getCount() {
        return authorDao.count();
    }

    @ShellMethod(value = "Insert author", key = {"insertAuthor"})
    public String insert(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorDao.insert(author);
        return String.format("Вы добавили нового автора: %s %s", author.getFirstName(), author.getLastName());
    }

    @ShellMethod(value = "Get author", key = {"getAuthor"})
    public String getById(long id) {
        Author author = authorDao.getById(id);
        return String.format("%s %s", author.getFirstName(), author.getLastName());
    }

    @ShellMethod(value = "Get all authors", key = {"getAllAuthors"})
    public void getAll() {
        List<Author> list = authorDao.getAll();
        list.forEach(author -> System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName())));
    }

    @ShellMethod(value = "Delete author", key = {"deleteAuthor"})
    public String deleteById(long id) {
        Author author = authorDao.getById(id);
        authorDao.deleteById(id);
        return String.format("Вы удалили автора: %s %s", author.getFirstName(), author.getLastName());
    }
}
