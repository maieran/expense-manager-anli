package com.anli.expensemana.service;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.SignUpDTO;
import com.anli.expensemana.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface UserService {
    User signUp(SignUpDTO userInput);
    //Boolean loginUser(LoginDTO userInput);

    String loginUser(LoginDTO userInput, HttpServletRequest request);
    String logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
    //Boolean logoutUser();
}
