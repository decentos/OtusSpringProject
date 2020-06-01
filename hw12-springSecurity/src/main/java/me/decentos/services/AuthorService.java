package me.decentos.services;

import lombok.RequiredArgsConstructor;
import me.decentos.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public long getCount() {
        return authorRepository.count();
    }

}
