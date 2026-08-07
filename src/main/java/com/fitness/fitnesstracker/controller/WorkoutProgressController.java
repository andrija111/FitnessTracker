package com.fitness.fitnesstracker.controller;

import com.fitness.fitnesstracker.dto.WorkoutProgressRequest;
import com.fitness.fitnesstracker.model.WorkoutProgress;
import com.fitness.fitnesstracker.service.WorkoutProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller responsible for workout progress tracking operations.
 * Provides endpoints for logging and retrieving user workout progress.
 */
@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@Tag(name = "Workout Progress", description = "Praćenje napretka korisnika")
@SecurityRequirement(name = "bearerAuth")
public class WorkoutProgressController {

    private final WorkoutProgressService workoutProgressService;

    /**
     * Logs a new workout progress entry for the authenticated user.
     *
     * @param request   contains weight and notes
     * @param principal the authenticated user
     * @return the saved progress entry
     */
    @PostMapping
    @Operation(summary = "Unesi napredak nakon treninga")
    public ResponseEntity<WorkoutProgress> logProgress(
            @Valid @RequestBody WorkoutProgressRequest request,
            Principal principal) {
        return ResponseEntity.ok(workoutProgressService.logProgress(principal.getName(), request));
    }

    /**
     * Retrieves all workout progress entries for the authenticated user.
     *
     * @param principal the authenticated user
     * @return list of progress entries
     */
    @GetMapping
    @Operation(summary = "Dohvati povijest napretka")
    public ResponseEntity<List<WorkoutProgress>> getProgress(Principal principal) {
        return ResponseEntity.ok(workoutProgressService.getProgress(principal.getName()));
    }
}