package com.anli.expensemana.model;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;


    public Role(String name) {
        this.name = name;
    }
    public Role() {

    }

}
