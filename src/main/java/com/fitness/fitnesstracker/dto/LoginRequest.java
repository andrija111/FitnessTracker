package com.fitness.fitnesstracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO representing the login request payload.
 * Contains the credentials required for user authentication.
 */
@Data
public class LoginRequest {

    /**
     * The email address of the user.
     * Must be a valid email format and cannot be blank.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * The password of the user.
     * Cannot be blank.
     */
    @NotBlank
    private String password;
}