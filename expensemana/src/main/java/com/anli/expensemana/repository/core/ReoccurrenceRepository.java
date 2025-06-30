package com.anli.expensemana.repository.core;

import com.anli.expensemana.model.core.ExpenseEntry;
import com.anli.expensemana.model.core.Reoccurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReoccurrenceRepository extends JpaRepository<Reoccurrence, Long> {
}
