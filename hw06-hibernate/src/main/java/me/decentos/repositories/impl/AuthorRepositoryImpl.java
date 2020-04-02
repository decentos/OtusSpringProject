package me.decentos.repositories.impl;

import me.decentos.model.Author;
import me.decentos.repositories.AuthorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() <=0 ) {
            em.persist(author);
            return author;
        }
        else {
            return em.merge(author);
        }
    }

    @Override
    public int count() {
        return em.createQuery("select count(*) from authors a", Long.class).getSingleResult().intValue();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from authors", Author.class).getResultList();
    }

    @Override
    public void updateAuthorById(long id, String firstName, String lastName) {
        Query query = em.createQuery("update authors a " +
                "set a.first_name = :firstName " +
                "set a.last_name = :lastName " +
                "where a.id = :id");
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from authors a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
