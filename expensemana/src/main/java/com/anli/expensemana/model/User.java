package com.anli.expensemana.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class User extends BaseEntity {


    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = Role.class)
    private Set<Role> roles;

    public User(String username, String firstName, String middleName, String lastName, String email, String password) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }



    /// ..
}
