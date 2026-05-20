package com.fitness.fitnesstracker.dto;

import com.fitness.fitnesstracker.model.Equipment;
import com.fitness.fitnesstracker.model.ExperienceLevel;
import com.fitness.fitnesstracker.model.FitnessGoal;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserProfileRequest {

    @NotNull
    private Integer age;

    @NotNull
    private Double height;

    @NotNull
    private Double weight;

    @NotNull
    private FitnessGoal goal;

    @NotNull
    private ExperienceLevel experienceLevel;

    @NotNull
    private Equipment equipment;

    private String injuries;
}