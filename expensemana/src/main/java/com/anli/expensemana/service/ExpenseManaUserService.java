package com.anli.expensemana.service;

import com.anli.expensemana.model.User;
import com.anli.expensemana.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


//TODO: Ersetzt den UserRepository im UserController, da hier mehr Business
@Service
public class ExpenseManaUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public ExpenseManaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(email + " not found"));

        Set<GrantedAuthority> authorizations = user.
                getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorizations
        );
    }

}
