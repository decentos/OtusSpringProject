package me.decentos.repository;

import me.decentos.dto.BookSearchResultDto;
import me.decentos.model.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {

    Flux<BookSearchResultDto> findBooksByTitleContainingIgnoreCase(String text);

    @Query("{ 'authors.lastName' : ?0 }")
    Flux<BookSearchResultDto> findBooksByAuthorsLastName(String name);

    @Query("{ 'genres.genreName' : ?0 }")
    Flux<BookSearchResultDto> findBooksByGenreName(String genre);

}
