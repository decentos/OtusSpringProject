package me.decentos.repositories.impl;

import me.decentos.model.Book;
import me.decentos.repositories.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() <=0 ) {
            em.persist(book);
            return book;
        }
        else {
            return em.merge(book);
        }
    }

    @Override
    public int count() {
        return em.createQuery("select count(*) from books b", Long.class).getSingleResult().intValue();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from books", Book.class).getResultList();
    }

    @Override
    public void updateBookById(long id, String title) {
        Query query = em.createQuery("update books b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from books b " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
