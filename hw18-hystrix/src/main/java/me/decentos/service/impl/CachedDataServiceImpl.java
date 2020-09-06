package me.decentos.service.impl;

import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import me.decentos.service.CachedDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CachedDataServiceImpl implements CachedDataService {

    @Override
    public List<Book> getCachedBooks() {
        List<Book> books = new ArrayList<>();
        books.add(cachedBook());
        return books;
    }

    @Override
    public Book getCachedBook() {
        return cachedBook();
    }

    private Book cachedBook() {
        Author author = new Author("cachedAuthorFirstName", "cachedAuthorLastName");
        Genre genre = new Genre("cachedGenreName");
        return new Book("cachedBookTitle", author, genre);
    }
}
