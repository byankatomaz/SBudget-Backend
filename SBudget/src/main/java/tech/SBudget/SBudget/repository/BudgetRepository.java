package tech.SBudget.SBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.SBudget.SBudget.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
