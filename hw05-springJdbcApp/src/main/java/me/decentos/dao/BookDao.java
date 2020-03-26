package me.decentos.dao;

import me.decentos.model.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book book);
    Book getById(long id);
    List<Book> getAll();
    void deleteById(long id);
}
