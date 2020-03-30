package me.decentos.dao.impl;

import me.decentos.model.Book;
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

@DisplayName("Dao для работы с Book должно")
@JdbcTest
@Import(BookDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int count = bookDao.count();
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertAuthor() {
        Book expected = new Book(11L, "Test title", 1L, 1L);
        bookDao.insert(expected);

        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать книгу по ее id")
    @Test
    void getById() {
        Book expected = new Book(1L, "Alice in Wonderland", 1L, 2L);

        Book actual = bookDao.getById(expected.getId());
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void getAll() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1L, "Alice in Wonderland", 1L, 2L));
        expected.add(new Book(2L, "Jane Eyre", 2L, 3L));
        expected.add(new Book(3L, "Don Quixote", 3L, 4L));
        expected.add(new Book(4L, "The Time Machine", 4L, 5L));
        expected.add(new Book(5L, "Anna Karenina", 5L, 1L));
        expected.add(new Book(6L, "Pride and Prejudice", 6L, 6L));
        expected.add(new Book(7L, "Childhood", 5L, 3L));
        expected.add(new Book(8L, "Boyhood", 5L, 3L));
        expected.add(new Book(9L, "Love in the Time of Cholera", 7L, 4L));
        expected.add(new Book(10L, "The Book of Calculation", 8L, 7L));

        List<Book> actual = bookDao.getAll();

        boolean sameList = expected.size() == actual.size();
        if (sameList) {
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getId() == actual.get(i).getId();
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getTitle().equals(actual.get(i).getTitle());
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getAuthorId() == actual.get(i).getAuthorId();
            }
            for (int i = 0; i < expected.size(); i++) {
                sameList = expected.get(i).getGenreId() == actual.get(i).getGenreId();
            }
        }
        assertThat(sameList);
    }

    @DisplayName("удалять книгу по ее id")
    @Test
    void deleteById() {
        bookDao.deleteById(1L);
        assertThatThrownBy(() -> bookDao.getById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageContaining("Incorrect result size: expected 1, actual 0");

    }
}