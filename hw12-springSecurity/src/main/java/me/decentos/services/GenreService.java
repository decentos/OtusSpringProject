package me.decentos.services;

import lombok.RequiredArgsConstructor;
import me.decentos.domain.Genre;
import me.decentos.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Optional<Genre> findGenreByName(final String genreName) {
        return genreRepository.findByGenreNameContainingIgnoreCase(genreName);
    }
}
