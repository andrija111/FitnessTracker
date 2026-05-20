package com.fitness.fitnesstracker.model;

/**
 * Enum representing the fitness experience level of a user.
 * Used by the AI to adjust workout intensity and complexity accordingly.
 */
public enum ExperienceLevel {

    /** User is new to fitness with little or no training experience. */
    BEGINNER,

    /** User has some training experience and basic fitness knowledge. */
    INTERMEDIATE,

    /** User has extensive training experience and advanced fitness knowledge. */
    ADVANCED
}