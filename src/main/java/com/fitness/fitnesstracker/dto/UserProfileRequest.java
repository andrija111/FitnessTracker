package com.fitness.fitnesstracker.dto;

import com.fitness.fitnesstracker.model.Equipment;
import com.fitness.fitnesstracker.model.ExperienceLevel;
import com.fitness.fitnesstracker.model.FitnessGoal;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO representing the user profile request payload.
 * Contains all fitness-related information required to create or update a user profile.
 */
@Data
public class UserProfileRequest {

    /**
     * The age of the user in years.
     * Cannot be null.
     */
    @NotNull
    private Integer age;

    /**
     * The height of the user in centimeters.
     * Cannot be null.
     */
    @NotNull
    private Double height;

    /**
     * The weight of the user in kilograms.
     * Cannot be null.
     */
    @NotNull
    private Double weight;

    /**
     * The fitness goal of the user (e.g. MUSCLE_GAIN, WEIGHT_LOSS).
     * Cannot be null.
     */
    @NotNull
    private FitnessGoal goal;

    /**
     * The experience level of the user (e.g. BEGINNER, INTERMEDIATE, ADVANCED).
     * Cannot be null.
     */
    @NotNull
    private ExperienceLevel experienceLevel;

    /**
     * The available equipment for the user (e.g. GYM, HOME, NO_EQUIPMENT).
     * Cannot be null.
     */
    @NotNull
    private Equipment equipment;

    /**
     * Optional field describing any injuries or physical limitations of the user.
     * Used by the AI to avoid exercises that could worsen the condition.
     */
    private String injuries;
}