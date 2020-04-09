package me.decentos.repositories.impl;

import me.decentos.model.Author;
import me.decentos.model.Book;
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

@DisplayName("Репозиторий на основе Jpa для работы со книгами должен")
@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldSaveBook() {
        Author author = new Author(9L, "Ivan", "Testov");
        Genre genre = new Genre(8L, "Comedic");
        Book expected = new Book(11L, "Test title", author, genre);
        repository.save(expected);

        Book actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вовзращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        int count = repository.count();
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("возвращать книгу по ее id")
    @Test
    void shouldFindExpectedBookById() {
        Author author = new Author(1L, "Lewis", "Carrol");
        Genre genre = new Genre(2L, "Fantasy");
        Book expected = new Book(1L, "Alice in Wonderland", author, genre);

        Book actual = repository.findById(expected.getId()).orElseThrow();
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1L, "Alice in Wonderland", new Author(1L, "Lewis", "Carrol"), new Genre(2L, "Fantasy")));
        expected.add(new Book(2L, "Jane Eyre", new Author(2L, "Charlotte", "Bronte"), new Genre(3L, "Autobiography")));
        expected.add(new Book(3L, "Don Quixote", new Author(3L, "Miguel", "de Cervantes"), new Genre(4L, "Novel")));
        expected.add(new Book(4L, "The Time Machine", new Author(4L, "Herbert", "Wells"), new Genre(5L, "Science-fiction")));
        expected.add(new Book(5L, "Anna Karenina", new Author(5L, "Leo", "Tolstoy"), new Genre(1L, "Literary realism")));
        expected.add(new Book(6L, "Pride and Prejudice", new Author(6L, "Jane", "Austen"), new Genre(6L, "Romance")));
        expected.add(new Book(7L, "Childhood", new Author(5L, "Leo", "Tolstoy"), new Genre(3L, "Autobiography")));
        expected.add(new Book(8L, "Boyhood", new Author(5L, "Leo", "Tolstoy"), new Genre(3L, "Autobiography")));
        expected.add(new Book(9L, "Love in the Time of Cholera", new Author(7L, "Gabriel", "García Márquez"), new Genre(4L, "Novel")));
        expected.add(new Book(10L, "The Book of Calculation", new Author(8L, "Leonardo", "Fibonacci"), new Genre(7L, "Mathematics")));

        List<Book> actual = repository.findAll();

        assertThat(expected.size()).isEqualTo(actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertThat(expected.get(i).getId()).isEqualTo(actual.get(i).getId());
            assertThat(expected.get(i).getTitle()).isEqualTo(actual.get(i).getTitle());

            assertThat(expected.get(i).getAuthor().getId()).isEqualTo(actual.get(i).getAuthor().getId());
            assertThat(expected.get(i).getAuthor().getFirstName()).isEqualTo(actual.get(i).getAuthor().getFirstName());
            assertThat(expected.get(i).getAuthor().getLastName()).isEqualTo(actual.get(i).getAuthor().getLastName());

            assertThat(expected.get(i).getGenre().getId()).isEqualTo(actual.get(i).getGenre().getId());
            assertThat(expected.get(i).getGenre().getGenre()).isEqualTo(actual.get(i).getGenre().getGenre());
        }
    }

    @DisplayName("изменять книгу по ее id")
    @Test
    void shouldUpdateBookById() {
        Book oldBook = repository.findById(1L).orElseThrow();
        String oldTitle = oldBook.getTitle();
        String oldAuthor = oldBook.getAuthor().getFirstName() + " " + oldBook.getAuthor().getLastName();
        String oldGenre = oldBook.getGenre().getGenre();

        Author author = new Author(8L, "Leonardo", "Fibonacci");
        Genre genre = new Genre(1L, "Literary realism");
        Book book = new Book(1L, "Test title", author, genre);

        repository.updateBookById(book);

        Book updateBook = repository.findById(1L).orElseThrow();
        String updateTitle = updateBook.getTitle();
        String updateAuthor = updateBook.getAuthor().getFirstName() + " " + updateBook.getAuthor().getLastName();
        String updateGenre = updateBook.getGenre().getGenre();

        assertThat(oldTitle).isNotEqualTo(updateTitle);
        assertThat(oldAuthor).isNotEqualTo(updateAuthor);
        assertThat(oldGenre).isNotEqualTo(updateGenre);
    }

    @DisplayName("удалять книгу по ее id")
    @Test
    void shouldDeleteBookById() {
        repository.deleteById(1L);
        assertThatThrownBy(() -> repository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No value present");
    }
}