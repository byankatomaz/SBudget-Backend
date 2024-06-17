package tech.SBudget.SBudget.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.SBudget.SBudget.Dtos.login.LoginRequest;
import tech.SBudget.SBudget.Dtos.register.RegisterRequest;
import tech.SBudget.SBudget.models.enumOptions.Roles;

@Table(name="users")
@Entity(name="User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String password;
    private Roles role;

    public Users(RegisterRequest registerRequest) {
        this.name = registerRequest.name();
        this.username = registerRequest.username();
        this.password = registerRequest.password();
        this.role = registerRequest.role();
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}
