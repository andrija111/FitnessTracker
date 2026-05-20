package com.fitness.fitnesstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Fitness Tracker API application.
 * Bootstraps the Spring Boot application context and starts the embedded Tomcat server.
 */
@SpringBootApplication
public class FitnessTrackerApiApplication {

    /**
     * Main method that launches the Spring Boot application.
     *
     * @param args command-line arguments passed at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(FitnessTrackerApiApplication.class, args);
    }
}