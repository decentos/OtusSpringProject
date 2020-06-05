package me.decentos.controller;

import lombok.RequiredArgsConstructor;
import me.decentos.dto.BookDto;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;
import me.decentos.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @RequestMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("book", new Book("", new Author("", ""), new Genre("")));
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("bookDto") BookDto bookDto, Model model) {
        Book book = BookDto.toEntity(bookDto);
        bookService.saveBook(book);
        model.addAttribute("books", bookService.findAll());
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("bookDto") BookDto bookDto) {
        Book book = BookDto.toEntity(bookDto);
        bookService.saveBook(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return "redirect:/";
    }

}
