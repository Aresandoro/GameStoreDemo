package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //register
    public User register(String email,String username, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email già registrata");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username già in uso");


        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(email, username, encodedPassword);
        return userRepository.save(newUser);
    }

        //login
        public User login(String email, String password){

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Utente non trovato"));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Password errata");
            }

            return user;
        }
    }

