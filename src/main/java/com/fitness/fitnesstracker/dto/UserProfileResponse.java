package com.fitness.fitnesstracker.dto;

import com.fitness.fitnesstracker.model.Equipment;
import com.fitness.fitnesstracker.model.ExperienceLevel;
import com.fitness.fitnesstracker.model.FitnessGoal;
import lombok.Data;

/**
 * DTO representing the user profile response payload.
 * Contains all user and fitness-related information returned by the API.
 */
@Data
public class UserProfileResponse {

    /**
     * The unique identifier of the user profile.
     */
    private Long id;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The age of the user in years.
     */
    private Integer age;

    /**
     * The height of the user in centimeters.
     */
    private Double height;

    /**
     * The weight of the user in kilograms.
     */
    private Double weight;

    /**
     * The fitness goal of the user (e.g. MUSCLE_GAIN, WEIGHT_LOSS).
     */
    private FitnessGoal goal;

    /**
     * The experience level of the user (e.g. BEGINNER, INTERMEDIATE, ADVANCED).
     */
    private ExperienceLevel experienceLevel;

    /**
     * The available equipment for the user (e.g. GYM, HOME, NO_EQUIPMENT).
     */
    private Equipment equipment;

    /**
     * Any injuries or physical limitations of the user.
     */
    private String injuries;
}