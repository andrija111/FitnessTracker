package com.fitness.fitnesstracker.dto;

import com.fitness.fitnesstracker.model.Equipment;
import com.fitness.fitnesstracker.model.ExperienceLevel;
import com.fitness.fitnesstracker.model.FitnessGoal;
import lombok.Data;

@Data
public class UserProfileResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double height;
    private Double weight;
    private FitnessGoal goal;
    private ExperienceLevel experienceLevel;
    private Equipment equipment;
    private String injuries;
}