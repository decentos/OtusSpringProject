package me.decentos.repository;

import lombok.RequiredArgsConstructor;
import me.decentos.model.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate template;

    @Override
    public Flux<String> findDistinctAuthorLastName() {
        return Flux.fromIterable(
                template.query(Book.class)
                        .distinct("authors.lastName")
                        .as(String.class).all()
        );
    }

    @Override
    public Flux<String> findDistinctGenres() {
        return Flux.fromIterable(
                template.query(Book.class)
                        .distinct("genres.genreName")
                        .as(String.class).all()
        );
    }

}
