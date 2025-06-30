package com.anli.expensemana.service;

import com.anli.expensemana.config.JwtService;
import com.anli.expensemana.model.DTO.LoginDTO;
import com.anli.expensemana.model.DTO.SignUpDTO;
import com.anli.expensemana.model.User;
import com.anli.expensemana.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;


//TODO: Ersetzt den UserRepository im UserController, da hier mehr Business
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
                    passwordEncoder.encode(userInput.getPassword()), //userInput.getPassword()
                    userInput.getRole()//todo: later it should be switchable between system_admin, group_admin, user and etc.
            );
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    @Override
    public String loginUser(LoginDTO userInput, HttpServletRequest request) {
        Authentication authRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(userInput.getEmail(), userInput.getPassword());

        Authentication authResponse = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authResponse);

        UserDetails userDetails = (UserDetails) authResponse.getPrincipal();
        return jwtService.generateToken(userDetails.getUsername()); // ✅ Hier Email (Subject) übergeben
    }


    @Override
    public String logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            request.getSession().removeAttribute("SIMPLE_SECURITY_TOKEN");
            request.getSession().invalidate();
            SecurityContextHolder.clearContext();
            return new String("Logged out successfully");
        }

        return new String("Not authorized");

    }


}
