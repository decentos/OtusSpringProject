package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.dao.GenreDao;
import me.decentos.model.Genre;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class OperationsForGenres {

    private final GenreDao genreDao;

    @ShellMethod(value = "Get count of genres", key = {"genresCount"})
    public int getCount() {
        return genreDao.count();
    }

    @ShellMethod(value = "Insert genre", key = {"insertGenre"})
    public String insert(String genreName) {
        Genre genre = new Genre(genreName);
        genreDao.insert(genre);
        return String.format("Вы добавили новый жанр: %s", genre.getGenre());
    }

    @ShellMethod(value = "Get genre", key = {"getGenre"})
    public String getById(long id) {
        Genre genre = genreDao.getById(id);
        return genre.getGenre();
    }

    @ShellMethod(value = "Get all genres", key = {"getAllGenres"})
    public void getAll() {
        List<Genre> list = genreDao.getAll();
        list.forEach(genre -> System.out.println(genre.getGenre()));
    }

    @ShellMethod(value = "Delete genre", key = {"deleteGenre"})
    public String deleteById(long id) {
        Genre genre = genreDao.getById(id);
        genreDao.deleteById(id);
        return String.format("Вы удалили жанр: %s", genre.getGenre());
    }
}
