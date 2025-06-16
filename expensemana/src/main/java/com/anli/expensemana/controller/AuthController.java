package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class controls users' authentication and session to the platform.
 */
//see: https://medium.com/@villysiu/java-springboot-signup-login-rest-api-d01b21759ba9
//TODO: JWT-Extension + E-mail reset -> https://dev.to/mspilari/login-system-with-jwt-token-and-email-reset-password-571
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Handles user login and logout")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO userRequest, HttpServletRequest request) {
        try {
            String message = userService.loginUser(userRequest, request);
            return ResponseEntity.ok(message);
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }


//        try {
//            String isAuthenticated = userService.loginUser(userRequest, request);
//            return new ResponseEntity<>(isAuthenticated, HttpStatus.OK);
//
////            if (isAuthenticated) {
////                return ResponseEntity.ok(true);
////            } else {
////                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////            }
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String loggedOutUser = userService.logoutUser(request, response, authentication);
        if (loggedOutUser != null || loggedOutUser.isEmpty()) {
            return new ResponseEntity<>(loggedOutUser, HttpStatus.OK);
        }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            request.getSession().invalidate(); //kills user session
//            userService.logoutUser(); //clears security context
//            return ResponseEntity.ok("Logout successful.");
//        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in.");
    }
}
