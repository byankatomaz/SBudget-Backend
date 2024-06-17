package tech.SBudget.SBudget.Dtos.register;

import tech.SBudget.SBudget.models.enumOptions.Roles;

public record RegisterRequest(
        String name,
        String username,
        String password,
        Roles role
) {
}
