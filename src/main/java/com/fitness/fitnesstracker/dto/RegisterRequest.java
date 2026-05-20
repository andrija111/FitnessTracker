package com.fitness.fitnesstracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO representing the registration request payload.
 * Contains all required information for creating a new user account.
 */
@Data
public class RegisterRequest {

    /**
     * The email address of the new user.
     * Must be a valid email format and cannot be blank.
     */
    @NotBlank
    @Email
    private String email;

    /**
     * The password for the new user account.
     * Cannot be blank.
     */
    @NotBlank
    private String password;

    /**
     * The first name of the new user.
     * Cannot be blank.
     */
    @NotBlank
    private String firstName;

    /**
     * The last name of the new user.
     * Cannot be blank.
     */
    @NotBlank
    private String lastName;
}