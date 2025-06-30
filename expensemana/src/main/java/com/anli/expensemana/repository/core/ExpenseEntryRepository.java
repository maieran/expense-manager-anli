package com.anli.expensemana.repository.core;

import com.anli.expensemana.model.core.ExpenseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseEntryRepository extends JpaRepository<ExpenseEntry, Long> {
}
