package me.decentos.repositories;

import me.decentos.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);

    int count();

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void updateGenreById(Genre genre);

    void deleteById(long id);
}
