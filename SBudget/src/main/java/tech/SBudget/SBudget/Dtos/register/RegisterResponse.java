package tech.SBudget.SBudget.Dtos.register;

import tech.SBudget.SBudget.models.Users;
import tech.SBudget.SBudget.models.enumOptions.Roles;

public record RegisterResponse(

        String name,
        String username,
        Roles role

) {
    public RegisterResponse(Users user) {
        this(user.getName(), user.getUsername(), user.getRole());
    }
}
