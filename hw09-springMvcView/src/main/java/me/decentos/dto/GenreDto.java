package me.decentos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.decentos.model.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private long id;
    private String genre;

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getGenre());
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenre());
    }
}
