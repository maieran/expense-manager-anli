package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.SignUpDTO;
import com.anli.expensemana.model.User;
import com.anli.expensemana.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "SignUp", description = "Handles user SignUp")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //user/signup
    @PostMapping("/signup")
    public ResponseEntity<User> signupNewUser (@RequestBody SignUpDTO userInput) {
        try {
            User createdUser = userService.signUp(userInput);
            if (createdUser != null) {
                return ResponseEntity.ok(createdUser);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //user/profile
    //todo: later

    //user/update
    //todo: later

}
