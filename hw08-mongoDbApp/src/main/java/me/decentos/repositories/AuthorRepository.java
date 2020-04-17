package me.decentos.repositories;

import me.decentos.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAuthorByFirstNameContainingIgnoreCase(String firstName);
}
