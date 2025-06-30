package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.JwtResponseDTO;
import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    /*
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
    } */


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        JwtResponseDTO response = userService.loginUser(loginDTO, request);
        return ResponseEntity.ok(response.getToken()); // <-- NUR der String
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

    @GetMapping("/me")
    public ResponseEntity<?> whoami(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        System.out.println("Authenticated user: " + authentication.getName());
        return ResponseEntity.ok("You are: " + authentication.getName());
    }


}
