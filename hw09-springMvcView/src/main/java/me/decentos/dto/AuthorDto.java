package me.decentos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.decentos.model.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long id;
    private String firstName;
    private String lastName;

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getFirstName(), dto.getLastName());
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
}
