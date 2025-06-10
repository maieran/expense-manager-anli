package com.anli.expensemana.service;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.SignUpDTO;
import com.anli.expensemana.model.User;
import com.anli.expensemana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


//TODO: Ersetzt den UserRepository im UserController, da hier mehr Business
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Overriden by using email instead of username to login the user into ExpenseMana platform.
     * @param email
     * @return
     */
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

    @Override
    public User signUp(SignUpDTO userInput) {
        //TODO: validate the userInput,because we dont trust the backend nor the frontend
        if (userInput != null) {
            User newUser = new User(
                    userInput.getUserName(),
                    userInput.getFirstName(),
                    userInput.getMiddleName(),
                    userInput.getLastName(),
                    userInput.getEmail(),
                    userInput.getPassword(),
                    userInput.getRole()//todo: later it should be switchable between system_admin, group_admin, user and etc.
            );
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    @Override
    public Boolean loginUser(LoginDTO userInput) {
        Optional<User> userOptional = userRepository.findByEmail(userInput.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Objects.equals(userInput.getPassword(), user.getPassword());
        }
        return false;
    }

    @Override
    public Boolean logoutUser() {
        SecurityContextHolder.clearContext();
        return true;
    }


}
