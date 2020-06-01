package me.decentos.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.decentos.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookService service;

    @GetMapping({"", "/", "/home"})
    public Mono<String> index(final Model model) {
        model.addAttribute("booksTotal", service.getBooksCount());
        model.addAttribute("authorsTotal", service.getAuthorsCount());
        model.addAttribute("genresTotal", service.getGenresCount());
        return Mono.just("index");
    }

}
