package me.decentos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.decentos.model.Author;
import me.decentos.model.Book;
import me.decentos.model.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;
    private String title;
    private String firstName;
    private String lastName;
    private String genre;

    public static Book toEntity(BookDto dto) {
        Author author = new Author(dto.getFirstName(), dto.getLastName());
        Genre genre = new Genre(dto.getGenre());
        return new Book(dto.getId(), dto.getTitle(), author, genre);
    }
}
