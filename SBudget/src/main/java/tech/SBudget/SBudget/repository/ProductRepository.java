package tech.SBudget.SBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.SBudget.SBudget.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
