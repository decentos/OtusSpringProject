package me.decentos.service;

import lombok.AllArgsConstructor;
import me.decentos.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    final private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<me.decentos.model.User> userOpt = userRepository.findByName(username);
        return userOpt.map(u -> User.withUsername(u.getName()).password(u.getPassword()).authorities(Collections.emptyList()).build())
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
