package com.anli.expensemana.model.core;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExpenseEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reoccurrence_id")
    Reoccurrence reoccurrence;
    LocalDate date;
    BigDecimal amount;
    Long userId; // oder User user; (je nach Auth-Design)
}
