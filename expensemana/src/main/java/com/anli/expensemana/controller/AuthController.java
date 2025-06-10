package com.anli.expensemana.controller;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class controls users' authentication and session to the platform.
 */
//see: https://medium.com/@villysiu/java-springboot-signup-login-rest-api-d01b21759ba9
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO request) {
        try {
            boolean isAuthenticated = userService.loginUser(request);
            if (isAuthenticated) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            request.getSession().invalidate(); //kills user session
            userService.logoutUser(); //clears security context
            return ResponseEntity.ok("Logout successful.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in.");
    }
}
