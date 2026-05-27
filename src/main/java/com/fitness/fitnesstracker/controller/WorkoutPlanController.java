package com.fitness.fitnesstracker.controller;

import com.fitness.fitnesstracker.model.WorkoutPlan;
import com.fitness.fitnesstracker.service.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller responsible for AI-generated workout plan operations.
 * Provides endpoints for generating and retrieving workout plans.
 */
@RestController
@RequestMapping("/api/workout")
@RequiredArgsConstructor
@Tag(name = "Workout Plans", description = "AI generiranje i dohvaćanje planova treninga")
@SecurityRequirement(name = "bearerAuth")
public class WorkoutPlanController {

    private final AiService aiService;

    /**
     * Generates a personalized workout plan for the authenticated user.
     *
     * @param principal the authenticated user
     * @return AI-generated workout plan as a String
     */
    @PostMapping("/generate")
    @Operation(summary = "Generiraj personalizirani plan treninga")
    public ResponseEntity<String> generatePlan(Principal principal) {
        return ResponseEntity.ok(aiService.generateWorkoutPlan(principal.getName()));
    }

    /**
     * Retrieves all previously generated workout plans for the authenticated user.
     *
     * @param principal the authenticated user
     * @return list of workout plans
     */
    @GetMapping
    @Operation(summary = "Dohvati sve planove treninga")
    public ResponseEntity<List<WorkoutPlan>> getPlans(Principal principal) {
        return ResponseEntity.ok(aiService.getWorkoutPlans(principal.getName()));
    }
}