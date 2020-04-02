package me.decentos.repositories.impl;

import me.decentos.model.Genre;
import me.decentos.repositories.GenreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <=0 ) {
            em.persist(genre);
            return genre;
        }
        else {
            return em.merge(genre);
        }
    }

    @Override
    public int count() {
        return em.createQuery("select count(*) from genres g", Long.class).getSingleResult().intValue();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from genres", Genre.class).getResultList();
    }

    @Override
    public void updateGenreById(long id, String genre) {
        Query query = em.createQuery("update genres g " +
                "set g.genre = :genre " +
                "where g.id = :id");
        query.setParameter("genre", genre);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from genres g " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
