package me.decentos.service;

import me.decentos.model.Book;

import java.util.List;

public interface CachedDataService {
    List<Book> getCachedBooks();

    Book getCachedBook();
}
