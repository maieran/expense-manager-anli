package com.anli.expensemana.model.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reoccurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Boolean reoccurrence;
    String reoccurrenceType; // "Daily", "Weekly", etc. TODO: ins enum umwandeln
}
