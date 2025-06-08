package com.anli.expensemana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public BaseEntity() {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
