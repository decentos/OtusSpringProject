package me.decentos.repositories.impl;

import me.decentos.model.Book;
import me.decentos.model.Comment;
import me.decentos.repositories.CommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public int count() {
        return em.createQuery("select count(*) from Comment c", Long.class).getSingleResult().intValue();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public List<Comment> findAllByBookId(int bookId) {
        return em.createQuery("select c from Comment c where c.book = :book", Comment.class)
                .setParameter("book", new Book().byId(bookId))
                .getResultList();
    }

    @Override
    public void updateCommentById(Comment comment) {
        em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
