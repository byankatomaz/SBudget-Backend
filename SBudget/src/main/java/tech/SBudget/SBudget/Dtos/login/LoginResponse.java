package tech.SBudget.SBudget.Dtos.login;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
