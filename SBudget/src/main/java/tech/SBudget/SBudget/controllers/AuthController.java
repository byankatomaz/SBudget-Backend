package tech.SBudget.SBudget.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.SBudget.SBudget.Dtos.login.LoginRequest;
import tech.SBudget.SBudget.Dtos.login.LoginResponse;
import tech.SBudget.SBudget.Dtos.register.RegisterRequest;
import tech.SBudget.SBudget.Dtos.register.RegisterResponse;
import tech.SBudget.SBudget.services.UserService;

@RestController

public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }
}
