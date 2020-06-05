package me.decentos.service;

import lombok.AllArgsConstructor;
import me.decentos.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        String roleToAssign = getRoleToAssign(username);

        Optional<me.decentos.model.User> userOpt = userRepository.findByName(username);
        return userOpt.map(u -> User.withUsername(u.getName()).password(u.getPassword()).authorities(Collections.singletonList(new SimpleGrantedAuthority(roleToAssign))).build())
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }

    private String getRoleToAssign(String username) {
        String roleToAssign;
        if ("admin".equals(username)) {
            roleToAssign = "ROLE_ADMIN";
        } else {
            roleToAssign = "ROLE_USER";
        }
        return roleToAssign;
    }
}
