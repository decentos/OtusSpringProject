package me.decentos.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.decentos.dto.BookAddFormDto;
import me.decentos.model.Book;
import me.decentos.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookAddFormController {

    private final BookService bookService;

    @GetMapping("/library/books/add")
    public Mono<String> getForm() {
        return Mono.just("book_add_new");
    }

    @PostMapping("/library/books/add")
    public Mono<String> bookAddForm(@Valid @ModelAttribute("bookAddFormDto") final BookAddFormDto bookAddFormDto,
                                    final BindingResult result) {
        if (result.hasErrors()) {
            log.debug("{}", result);
            return Mono.just("book_add_new");
        }
        log.info("{}", bookAddFormDto);
        final Book book = bookAddFormDto.toEntity(bookAddFormDto);
        bookService.saveBook(book).subscribe();
        return Mono.just("redirect:/home");
    }

    @ModelAttribute("bookAddFormDto")
    public BookAddFormDto getBookDto() {
        return new BookAddFormDto();
    }

}
