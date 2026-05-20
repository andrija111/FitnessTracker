package com.fitness.fitnesstracker.model;

/**
 * Enum representing the available equipment options for a user.
 * Used by the AI to generate appropriate workout plans based on available resources.
 */
public enum Equipment {

    /** Full gym access with all equipment available. */
    GYM,

    /** Home workout with basic or no equipment. */
    HOME,

    /** No equipment available, bodyweight exercises only. */
    NO_EQUIPMENT
}