package me.decentos.repositories;

import me.decentos.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    int count();

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findAllByBookId(long bookId);

    void updateCommentById(Comment comment);

    void deleteById(long id);
}
