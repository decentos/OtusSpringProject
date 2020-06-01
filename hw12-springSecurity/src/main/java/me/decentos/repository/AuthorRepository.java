package me.decentos.repository;

import me.decentos.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE" +
            " LOWER(CONCAT(TRIM(a.firstName), TRIM(a.lastName))) " +
            "LIKE LOWER(CONCAT('%', TRIM(:name), '%'))")
    Iterable<Author> findByAuthorName(@Param("name") String name);

}
