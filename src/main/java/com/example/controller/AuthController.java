package com.example.controller;
import com.example.repository.UserRepository;
import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.entity.User;
import com.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        try{
        User user = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        AuthResponse response= new AuthResponse(
                user.getEmail(),
                "Login riuscito"
        );

        return ResponseEntity.ok(response);
    } catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            User newUser = authService.register(
                    request.getEmail(),
                    request.getPassword()
            );

            AuthResponse response = new AuthResponse(
                    newUser.getEmail(),
                    "Registrazione riuscita"
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}

