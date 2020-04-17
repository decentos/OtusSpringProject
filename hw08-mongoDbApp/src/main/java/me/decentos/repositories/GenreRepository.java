package me.decentos.repositories;

import me.decentos.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    @Query("{'genreName': { $regex: ?0, $options: 'i' }}")
    List<Genre> findGenresByName(String genre);
}
