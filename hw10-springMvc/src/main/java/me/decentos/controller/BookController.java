package me.decentos.controller;

import me.decentos.dto.BookDto;
import me.decentos.model.Book;
import me.decentos.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/book/{id}")
    public BookDto getBook(@PathVariable("id") long id) {
        return BookDto.toDto(repository.findById(id).orElseThrow());
    }

    @PostMapping("/api/edit")
    public void createOrUpdate(@RequestBody BookDto bookDto) {
        Book book = BookDto.toEntity(bookDto);
        repository.save(book);
    }

    @DeleteMapping("/api/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

}
