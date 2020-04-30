package me.decentos.controller;

import me.decentos.dto.BookDto;
import me.decentos.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/api/create")
//    public String create(@ModelAttribute("bookDto") BookDto bookDto, Model model) {
//        Book book = BookDto.toEntity(bookDto);
//        repository.save(book);
//        model.addAttribute("books", repository.findAll());
//        return "redirect:/";
//    }
//
//    @PostMapping("/api/edit")
//    public String update(@ModelAttribute("bookDto") BookDto bookDto) {
//        Book book = BookDto.toEntity(bookDto);
//        repository.save(book);
//        return "redirect:/";
//    }
//
    @DeleteMapping("/api/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

}
