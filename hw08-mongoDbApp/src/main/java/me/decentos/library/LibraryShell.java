package me.decentos.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class LibraryShell {

    private final LibraryService service;

    @Autowired
    public LibraryShell(final LibraryService service) {
        this.service = service;
    }

    @ShellMethod(value = "Find all books in the library.")
    public void findAllBooks() {
        service.findAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "Find books by title.")
    public void findBookByTitle(final String title) {
        service.findBooksByTitle(title).forEach(System.out::println);
    }

    @ShellMethod(value = "Find all authors.")
    public void findAllAuthors() {
        service.findAllAuthors().forEach(System.out::println);
    }

    @ShellMethod(value = "Find authors by first name.")
    public void findAuthorsByFirstName(final String firstName) {
        service.findAuthorsByFirstName(firstName).forEach(System.out::println);
    }

    @ShellMethod(value = "Find all genres.")
    public void findAllGenres() {
        service.findAllGenres().forEach(System.out::println);
    }

    @ShellMethod(value = "Find genres by genre name.")
    public void findGenresByName(final String genreName) {
        service.findGenresByName(genreName).forEach(System.out::println);
    }
}
