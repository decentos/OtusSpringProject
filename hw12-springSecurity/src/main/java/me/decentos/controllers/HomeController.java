package me.decentos.controllers;

import lombok.RequiredArgsConstructor;
import me.decentos.services.AuthorService;
import me.decentos.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/home")
    public String index(final Model model) {
        model.addAttribute("booksTotal", bookService.getCount());
        model.addAttribute("authorsTotal", authorService.getCount());
        return "index";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

}
