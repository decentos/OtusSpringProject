package me.decentos.repository;

import me.decentos.domain.Book;
import me.decentos.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Iterable<Comment> findCommentsByBook(Book book);

    Iterable<Comment> findByCommentaryContainingIgnoreCase(String text);

}
