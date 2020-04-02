package me.decentos.repositories;

import me.decentos.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    int count();

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateBookById(Book book);

    void deleteById(long id);
}
