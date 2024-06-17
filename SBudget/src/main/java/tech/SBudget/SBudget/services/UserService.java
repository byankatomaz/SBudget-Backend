package tech.SBudget.SBudget.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import tech.SBudget.SBudget.Dtos.login.LoginRequest;
import tech.SBudget.SBudget.Dtos.login.LoginResponse;
import tech.SBudget.SBudget.Dtos.register.RegisterRequest;
import tech.SBudget.SBudget.Dtos.register.RegisterResponse;
import tech.SBudget.SBudget.models.Users;
import tech.SBudget.SBudget.repository.UsersRepository;

import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;


    public LoginResponse login(LoginRequest loginRequest) {
        var user = repository.findByUsername(loginRequest.username());
        var now = Instant.now();
        var expiresIn = 300L;
        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("User or password is invalid");
        }

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(String.valueOf(user.get().getId()))
                .expiresAt(now.plusSeconds(expiresIn))
                .build();


        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        var userExister = repository.findByUsername(registerRequest.username());

        if (userExister.isPresent()) {
            throw new IllegalArgumentException("This user already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());

        var user = new Users(registerRequest);

        user.setPassword(encryptedPassword);
        repository.save(user);

        return new RegisterResponse(user);
    }

}
