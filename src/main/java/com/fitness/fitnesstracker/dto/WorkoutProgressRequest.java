package com.fitness.fitnesstracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO representing the workout progress request payload.
 * Contains the data a user submits after completing a workout session.
 */
@Data
public class WorkoutProgressRequest {

    /**
     * The current weight of the user in kilograms.
     * Cannot be null.
     */
    @NotNull
    private Double weight;

    /**
     * User notes about the workout session.
     * Can include exercises performed, difficulty level, or any observations.
     */
    private String notes;
}