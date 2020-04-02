package me.decentos.shell;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Genre;
import me.decentos.repositories.GenreRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommandsForGenres {

    private final GenreRepository genreRepository;

    @ShellMethod(value = "Get count of genres", key = {"genresCount"})
    public int getCount() {
        return genreRepository.count();
    }

    @ShellMethod(value = "Save genre", key = {"saveGenre"})
    public String save(String genreName) {
        Genre genre = new Genre(genreName);
        genreRepository.save(genre);
        return String.format("Вы добавили новый жанр: %s", genre.getGenre());
    }

    @ShellMethod(value = "Find genre", key = {"findGenre"})
    public String findById(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return genre.getGenre();
    }

    @ShellMethod(value = "Find all genres", key = {"findAllGenres"})
    public void getAll() {
        List<Genre> list = genreRepository.findAll();
        list.forEach(genre -> System.out.println(genre.getGenre()));
    }

    @ShellMethod(value = "Update genre", key = {"updateGenre"})
    public String updateAuthorById(long id, String genreName) {
        Genre oldGenre = genreRepository.findById(id).orElseThrow();
        String oldGenreName = oldGenre.getGenre();

        genreRepository.updateGenreById(new Genre(id, genreName));

        Genre updateGenre = genreRepository.findById(id).orElseThrow();
        String updateGenreName = updateGenre.getGenre();

        return String.format("Вы успешло изменили жанр с %s на %s", oldGenreName, updateGenreName);
    }

    @ShellMethod(value = "Delete genre", key = {"deleteGenre"})
    public String deleteById(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        genreRepository.deleteById(id);
        return String.format("Вы удалили жанр: %s", genre.getGenre());
    }
}
