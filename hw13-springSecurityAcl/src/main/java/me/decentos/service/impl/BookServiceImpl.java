package me.decentos.service.impl;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Book;
import me.decentos.repositories.BookRepository;
import me.decentos.service.BookService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void saveBook(Book book) {
        repository.save(book);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
