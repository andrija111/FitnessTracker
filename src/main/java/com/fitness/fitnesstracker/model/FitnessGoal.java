package com.fitness.fitnesstracker.model;

/**
 * Enum representing the fitness goal of a user.
 * Used by the AI to generate a personalized workout plan aligned with the user's objective.
 */
public enum FitnessGoal {

    /** User wants to lose weight through cardio and calorie-burning exercises. */
    WEIGHT_LOSS,

    /** User wants to build muscle mass through strength training. */
    MUSCLE_GAIN,

    /** User wants to improve cardiovascular endurance and stamina. */
    ENDURANCE,

    /** User wants to improve flexibility and mobility through stretching exercises. */
    FLEXIBILITY
}