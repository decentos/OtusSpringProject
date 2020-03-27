package me.decentos.dao.impl;

import me.decentos.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с Genre должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int count = genreDao.count();
        assertThat(count).isEqualTo(7);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertAuthor() {
        Genre expected = new Genre(8L, "Comedic");
        genreDao.insert(expected);

        Genre actual = genreDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать жанр по его id")
    @Test
    void getById() {
        Genre expected = new Genre(1L, "Literary realism");

        Genre actual = genreDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех жанров'")
    @Test
    void getAll() {
        List<Genre> expected = new ArrayList<>();
        expected.add(new Genre(1L, "Literary realism"));
        expected.add(new Genre(2L, "Fantasy"));
        expected.add(new Genre(3L, "Autobiography"));
        expected.add(new Genre(4L, "Novel"));
        expected.add(new Genre(5L, "Science-fiction"));
        expected.add(new Genre(6L, "Romance"));
        expected.add(new Genre(7L, "Mathematics"));

        List<Genre> actual = genreDao.getAll();

        boolean sameList = expected.size() == actual.size();
        if (sameList) {
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getId() == actual.get(i).getId();
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getGenre().equals(actual.get(i).getGenre());
            }
        }
        assertThat(sameList);
    }

    @DisplayName("удалять жанр по его id")
    @Test
    void deleteById() {
        genreDao.deleteById(1L);
        assertThatThrownBy(() -> genreDao.getById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageContaining("Incorrect result size: expected 1, actual 0");

    }
}