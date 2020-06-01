package me.decentos.controllers;

import lombok.RequiredArgsConstructor;
import me.decentos.dto.BookProjection;
import me.decentos.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookSearchController {

    private final BookService bookService;

    @GetMapping(value = "/library/books/search")
    public String bookSearchForm() {
        return "book_search_form";
    }

    @GetMapping(value = "/library/books/search/full")
    public String fullSearch(final Model model,
                             @RequestParam(required = false) final String title,
                             @RequestParam(required = false) final String author,
                             @RequestParam(required = false) final String genre) {

        if (allParamsNotNull(title, author, genre) && allParamsNotBlank(title, author, genre)) {
            List<BookProjection> books = bookService.findBooksByRequestParameters(title, author, genre);
            model.addAttribute("books", books);
        }
        return "book_search_result";
    }

    @GetMapping(value = "/library/books/search/quick")
    public String quickSearch(final Model model, @RequestParam(required = false) final String title) {
        if (!title.isBlank()) {
            List<BookProjection> books = bookService.findBooksByTitleRequestParam(title);
            model.addAttribute("books", books);
        }
        return "book_search_result";
    }

    private boolean allParamsNotBlank(final String title, final String author, final String genre) {
        return !title.isBlank() || !author.isBlank() || !genre.isBlank();
    }

    private boolean allParamsNotNull(final String title, final String author, final String genre) {
        return title != null && author != null && genre != null;
    }

}
