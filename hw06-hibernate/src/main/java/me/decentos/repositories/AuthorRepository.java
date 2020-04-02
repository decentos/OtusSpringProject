package me.decentos.repositories;

import me.decentos.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    int count();

    Optional<Author> findById(long id);

    List<Author> findAll();

    void updateAuthorById(long id, String firstName, String lastName);

    void deleteById(long id);
}
