package com.anli.expensemana.model.DTO;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class MeDTO {
    private String email;
    private List<String> roles;

    public MeDTO(String email, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    // Getters
}

