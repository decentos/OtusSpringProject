package me.decentos.repositories.impl;

import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Comment;
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

@DisplayName("Репозиторий на основе Jpa для работы со комментариями должен")
@DataJpaTest
@Import(CommentRepositoryImpl.class)
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepositoryImpl repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldSaveComment() {
        Author author = new Author(1L, "Lewis", "Carrol");
        Genre genre = new Genre(2L, "Fantasy");
        Book book = new Book(1L, "Alice in Wonderland", author, genre);
        Comment expected = new Comment(7L, "Top", book);
        repository.save(expected);

        Comment actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество комментариев в БД")
    @Test
    void shouldReturnExpectedCommentsCount() {
        int count = repository.count();
        assertThat(count).isEqualTo(6);
    }

    @DisplayName("возвращать комментарий по его id")
    @Test
    void shouldFindExpectedCommentById() {
        Author author = new Author(1L, "Lewis", "Carrol");
        Genre genre = new Genre(2L, "Fantasy");
        Book book = new Book(1L, "Alice in Wonderland", author, genre);
        Comment expected = new Comment(1L, "Excellent", book);

        Comment actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех комментариев")
    @Test
    void shouldReturnCorrectCommentsList() {
        List<Comment> expected = new ArrayList<>();
        expected.add(new Comment(1L, "Excellent", new Book(1L, "Alice in Wonderland", new Author(1L, "Lewis", "Carrol"), new Genre(2L, "Fantasy"))));
        expected.add(new Comment(2L, "Nice", new Book(2L, "Jane Eyre", new Author(2L, "Charlotte", "Bronte"), new Genre(3L, "Autobiography"))));
        expected.add(new Comment(3L, "Awesome book", new Book(2L, "Jane Eyre", new Author(2L, "Charlotte", "Bronte"), new Genre(3L, "Autobiography"))));
        expected.add(new Comment(4L, "Pretty good", new Book(3L, "Don Quixote", new Author(3L, "Miguel", "de Cervantes"), new Genre(4L, "Novel"))));
        expected.add(new Comment(5L, "Very good", new Book(5L, "Anna Karenina", new Author(5L, "Leo", "Tolstoy"), new Genre(1L, "Literary realism"))));
        expected.add(new Comment(6L, "Bad book", new Book(9L, "Love in the Time of Cholera", new Author(7L, "Gabriel", "García Márquez"), new Genre(4L, "Novel"))));

        List<Comment> actual = repository.findAll();

        assertThat(expected.size()).isEqualTo(actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertThat(expected.get(i).getId()).isEqualTo(actual.get(i).getId());
            assertThat(expected.get(i).getCommentary()).isEqualTo(actual.get(i).getCommentary());

            assertThat(expected.get(i).getBook().getId()).isEqualTo(actual.get(i).getBook().getId());
            assertThat(expected.get(i).getBook().getTitle()).isEqualTo(actual.get(i).getBook().getTitle());

            assertThat(expected.get(i).getBook().getAuthor().getId()).isEqualTo(actual.get(i).getBook().getAuthor().getId());
            assertThat(expected.get(i).getBook().getAuthor().getFirstName()).isEqualTo(actual.get(i).getBook().getAuthor().getFirstName());
            assertThat(expected.get(i).getBook().getAuthor().getLastName()).isEqualTo(actual.get(i).getBook().getAuthor().getLastName());

            assertThat(expected.get(i).getBook().getGenre().getId()).isEqualTo(actual.get(i).getBook().getGenre().getId());
            assertThat(expected.get(i).getBook().getGenre().getGenre()).isEqualTo(actual.get(i).getBook().getGenre().getGenre());
        }
    }

    @DisplayName("изменять комментарий по его id")
    @Test
    void shouldUpdateCommentById() {
        Comment oldComment = repository.findById(1L).orElseThrow();
        String oldCommentary = oldComment.getCommentary();

        Book book = oldComment.getBook();
        repository.updateCommentById(new Comment(1L, "Top", book));

        Comment updateComment = repository.findById(1L).orElseThrow();
        String updateCommentary = updateComment.getCommentary();

        assertThat(oldCommentary).isNotEqualTo(updateCommentary);
    }

    @DisplayName("удалять комментарий по его id")
    @Test
    void shouldDeleteCommentById() {
        repository.deleteById(1L);
        assertThatThrownBy(() -> repository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No value present");
    }
}