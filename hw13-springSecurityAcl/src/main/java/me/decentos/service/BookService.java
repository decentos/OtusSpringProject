package me.decentos.service;

import me.decentos.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(long id);

    void saveBook(Book book);

    void delete(long id);
}
