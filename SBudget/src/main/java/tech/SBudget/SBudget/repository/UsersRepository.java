package tech.SBudget.SBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.SBudget.SBudget.models.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
