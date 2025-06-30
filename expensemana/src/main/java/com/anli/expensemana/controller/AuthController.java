package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.MeDTO;
import com.anli.expensemana.service.TokenBlacklistServiceImpl;
import com.anli.expensemana.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    private TokenBlacklistServiceImpl tokenBlacklistServiceImpl;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO userRequest, HttpServletRequest request) {
        try {
            String jwtToken = userService.loginUser(userRequest, request);
            return ResponseEntity.ok(jwtToken); // ⬅️ return the token
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistServiceImpl.blacklist(token);
            return ResponseEntity.ok("Logged out successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No token provided");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        return ResponseEntity.ok(new MeDTO(userDetails.getUsername(), userDetails.getAuthorities()));
    }



}
