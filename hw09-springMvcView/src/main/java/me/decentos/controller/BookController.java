package me.decentos.controller;

import me.decentos.dto.BookDto;
import me.decentos.model.Book;
import me.decentos.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = repository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = repository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute("bookDto") BookDto bookDto) {
        Book book = BookDto.toEntity(bookDto);
        repository.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

}
