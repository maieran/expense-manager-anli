package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.UserDTO;
import com.anli.expensemana.model.User;
import com.anli.expensemana.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signupNewUser (@RequestBody UserDTO request) {
        try {
            User createdUser = userRepository.save(new User(
                    request.userName,
                    request.firstName,
                    request.middleName,
                    request.lastName,
                    request.email,
                    request.password
            ));
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserDTO request) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(request.email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (Objects.equals(request.password, user.getPassword())) {
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserDTO request) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(request.email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (Objects.equals(request.password, user.getPassword())) {
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

}
