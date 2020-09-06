package me.decentos.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import me.decentos.model.Book;
import me.decentos.repositories.BookRepository;
import me.decentos.service.BookRepositoryService;
import me.decentos.service.CachedDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookRepositoryServiceImpl implements BookRepositoryService {
    private final BookRepository bookRepository;
    private final CachedDataService cachedDataService;

    @HystrixCommand(groupKey = "BookRepo", fallbackMethod = "getCachedBooks", commandKey = "findAllBooks")
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();

    }

    @HystrixCommand(groupKey = "BookRepo", fallbackMethod = "getCachedBookById", commandKey = "findBookById")
    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    private List<Book> getCachedBooks() {
        return cachedDataService.getCachedBooks();
    }

    private Book getCachedBookById(Long id) {
        return cachedDataService.getCachedBook();
    }
}
