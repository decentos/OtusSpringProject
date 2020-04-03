package me.decentos.repositories.impl;

import me.decentos.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Репозиторий на основе Jpa для работы со авторами должен")
@DataJpaTest
@Import(AuthorRepositoryImpl.class)
class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepositoryImpl repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldSaveAuthor() {
        Author expected = new Author(9L, "Ivan", "Test");
        repository.save(expected);

        Author actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorsCount() {
        int count = repository.count();
        assertThat(count).isEqualTo(8);
    }

    @DisplayName("загружать информацию о нужном авторе по его id")
    @Test
    void shouldFindExpectedAuthorById() {
        Author expected = new Author(1L, "Lewis", "Carrol");

        Author actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех авторов")
    @Test
    void shouldReturnCorrectAuthorsList() {
        List<Author> expected = new ArrayList<>();
        expected.add(new Author(1L, "Lewis", "Carrol"));
        expected.add(new Author(2L, "Charlotte", "Bronte"));
        expected.add(new Author(3L, "Miguel", "de Cervantes"));
        expected.add(new Author(4L, "Herbert", "Wells"));
        expected.add(new Author(5L, "Leo", "Tolstoy"));
        expected.add(new Author(6L, "Jane", "Austen"));
        expected.add(new Author(7L, "Gabriel", "García Márquez"));
        expected.add(new Author(8L, "Leonardo", "Fibonacci"));

        List<Author> actual = repository.findAll();

        assertThat(expected.size()).isEqualTo(actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertThat(expected.get(i).getId()).isEqualTo(actual.get(i).getId());
            assertThat(expected.get(i).getFirstName()).isEqualTo(actual.get(i).getFirstName());
            assertThat(expected.get(i).getLastName()).isEqualTo(actual.get(i).getLastName());
        }
    }

    @DisplayName("изменять автора по его id")
    @Test
    void shouldUpdateAuthorById() {
        Author oldAuthor = repository.findById(1L).orElseThrow();
        String oldFirstName = oldAuthor.getFirstName();
        String oldLastName = oldAuthor.getLastName();

        repository.updateAuthorById(new Author(1L, "Ivan", "Test"));

        Author updateAuthor = repository.findById(1L).orElseThrow();
        String updateFirstName = updateAuthor.getFirstName();
        String updateLastName = updateAuthor.getLastName();

        assertThat(oldFirstName).isNotEqualTo(updateFirstName);
        assertThat(oldLastName).isNotEqualTo(updateLastName);
    }

    @DisplayName("удалять автора по его id")
    @Test
    void shouldDeleteAuthorById() {
        repository.deleteById(1L);
        assertThatThrownBy(() -> repository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No value present");
    }
}