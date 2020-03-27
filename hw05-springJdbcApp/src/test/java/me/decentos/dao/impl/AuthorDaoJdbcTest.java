package me.decentos.dao.impl;

import me.decentos.model.Author;
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

@DisplayName("Dao для работы с Author должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int count = authorDao.count();
        assertThat(count).isEqualTo(8);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author expected = new Author(9L, "Ivan", "Test");
        authorDao.insert(expected);

        Author actual = authorDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать автора по его id")
    @Test
    void getById() {
        Author expected = new Author(1L, "Lewis", "Carrol");

        Author actual = authorDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех авторов")
    @Test
    void getAll() {
        List<Author> expected = new ArrayList<>();
        expected.add(new Author(1L, "Lewis", "Carrol"));
        expected.add(new Author(2L, "Charlotte", "Bronte"));
        expected.add(new Author(3L, "Miguel", "de Cervantes"));
        expected.add(new Author(4L, "Herbert", "Wells"));
        expected.add(new Author(5L, "Leo", "Tolstoy"));
        expected.add(new Author(6L, "Jane", "Austen"));
        expected.add(new Author(7L, "Gabriel", "García Márquez"));
        expected.add(new Author(8L, "Leonardo", "Fibonacci"));

        List<Author> actual = authorDao.getAll();

        boolean sameList = expected.size() == actual.size();
        if (sameList) {
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getId() == actual.get(i).getId();
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getFirstName().equals(actual.get(i).getFirstName());
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getLastName().equals(actual.get(i).getLastName());
            }
        }
        assertThat(sameList);
    }

    @DisplayName("удалять автора по его id")
    @Test
    void deleteById() {
        authorDao.deleteById(1L);
        assertThatThrownBy(() -> authorDao.getById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageContaining("Incorrect result size: expected 1, actual 0");

    }
}