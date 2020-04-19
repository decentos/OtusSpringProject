package me.decentos.library;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import me.decentos.repositories.AuthorRepository;
import me.decentos.repositories.BookRepository;
import me.decentos.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByTitle(final String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsByFirstName(final String firstName) {
        return authorRepository.findAuthorByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> findGenresByName(final String genreName) {
        return genreRepository.findGenresByName(genreName);
    }

}
