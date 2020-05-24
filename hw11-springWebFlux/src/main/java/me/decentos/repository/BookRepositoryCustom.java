package me.decentos.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepositoryCustom {

    Flux<String> findDistinctAuthorLastName();

    Flux<String> findDistinctGenres();

}
