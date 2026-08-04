package com.fitness.fitnesstracker.controller;

import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller responsible for admin operations.
 * Provides endpoints for user management and platform statistics.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Upravljanje korisnicima i statistike platforme")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final AdminService adminService;

    /**
     * Retrieves all registered users.
     *
     * @return list of all users
     */
    @GetMapping("/users")
    @Operation(summary = "Dohvati sve korisnike")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return success message
     */
    @DeleteMapping("/users/{id}")
    @Operation(summary = "Obriši korisnika")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("Korisnik uspješno obrisan!");
    }

    /**
     * Returns platform statistics.
     *
     * @return map of statistics
     */
    @GetMapping("/stats")
    @Operation(summary = "Statistike platforme")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
                "totalUsers", adminService.getTotalUsers()
        ));
    }
}