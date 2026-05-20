package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.dto.LoginRequest;
import com.fitness.fitnesstracker.dto.RegisterRequest;
import com.fitness.fitnesstracker.model.Role;
import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service responsible for user authentication logic.
 * Handles user registration and login, including password hashing and JWT token generation.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registers a new user in the system.
     * Checks if the email is already in use, hashes the password,
     * saves the user to the database and returns a JWT token.
     *
     * @param request contains email, password, first name and last name
     * @return JWT token for the newly registered user
     * @throws RuntimeException if a user with the given email already exists
     */
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Korisnik s tim emailom već postoji!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.USER);

        userRepository.save(user);

        return jwtService.generateToken(user.getEmail());
    }

    /**
     * Authenticates an existing user.
     * Verifies the email and password, and returns a JWT token if credentials are valid.
     *
     * @param request contains email and password
     * @return JWT token for the authenticated user
     * @throws RuntimeException if the user is not found or the password is incorrect
     */
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Pogrešna lozinka!");
        }

        return jwtService.generateToken(user.getEmail());
    }
}