package me.decentos.repositories.impl;

import me.decentos.model.Genre;
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

@DisplayName("Репозиторий на основе Jpa для работы со жанрами должен")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
class GenreRepositoryImplTest {

    @Autowired
    private GenreRepositoryImpl repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldSaveGenre() {
        Genre expected = new Genre(8L, "Comedic");
        repository.save(expected);

        Genre actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenresCount() {
        int count = repository.count();
        assertThat(count).isEqualTo(7);
    }

    @DisplayName("возвращать жанр по его id")
    @Test
    void shouldFindExpectedGenreById() {
        Genre expected = new Genre(1L, "Literary realism");

        Genre actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех жанров")
    @Test
    void shouldReturnCorrectGenresList() {
        List<Genre> expected = new ArrayList<>();
        expected.add(new Genre(1L, "Literary realism"));
        expected.add(new Genre(2L, "Fantasy"));
        expected.add(new Genre(3L, "Autobiography"));
        expected.add(new Genre(4L, "Novel"));
        expected.add(new Genre(5L, "Science-fiction"));
        expected.add(new Genre(6L, "Romance"));
        expected.add(new Genre(7L, "Mathematics"));

        List<Genre> actual = repository.findAll();

        assertThat(expected.size()).isEqualTo(actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertThat(expected.get(i).getId()).isEqualTo(actual.get(i).getId());
            assertThat(expected.get(i).getGenre()).isEqualTo(actual.get(i).getGenre());
        }
    }

    @DisplayName("изменять жанр по его id")
    @Test
    void shouldUpdateGenreById() {
        Genre oldGenre = repository.findById(1L).orElseThrow();
        String oldGenreName = oldGenre.getGenre();

        repository.updateGenreById(new Genre(1L, "Comedic"));

        Genre updateGenre = repository.findById(1L).orElseThrow();
        String updateGenreName = updateGenre.getGenre();

        assertThat(oldGenreName).isNotEqualTo(updateGenreName);
    }

    @DisplayName("удалять жанр по его id")
    @Test
    void shouldDeleteGenreById() {
        repository.deleteById(1L);
        assertThatThrownBy(() -> repository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No value present");
    }
}