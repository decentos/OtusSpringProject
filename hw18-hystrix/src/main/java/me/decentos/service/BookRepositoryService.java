package me.decentos.service;

import me.decentos.model.Book;

import java.util.List;

public interface BookRepositoryService {
    List<Book> findAll();

    Book findById(Long id);

    void deleteById(Long id);

    void save(Book book);
}
