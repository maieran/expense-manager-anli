package com.anli.expensemana.service;

import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.SignUpDTO;
import com.anli.expensemana.model.User;

public interface UserService {
    User signUp(SignUpDTO userInput);
    Boolean loginUser(LoginDTO userInput);
    Boolean logoutUser();
}
