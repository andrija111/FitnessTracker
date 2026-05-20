package com.fitness.fitnesstracker.model;

/**
 * Enum representing the role of a user in the system.
 * Used by Spring Security to determine access rights and permissions.
 */
public enum Role {

    /** Standard user with access to personal profile and workout plans. */
    USER,

    /** Administrator with full access to all users and system statistics. */
    ADMIN
}