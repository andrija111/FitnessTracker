package com.fitness.fitnesstracker.controller;

import com.fitness.fitnesstracker.dto.UserProfileRequest;
import com.fitness.fitnesstracker.dto.UserProfileResponse;
import com.fitness.fitnesstracker.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Tag(name = "User Profile", description = "Upravljanje korisničkim profilom")
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    @Operation(summary = "Kreiranje ili ažuriranje profila")
    public ResponseEntity<UserProfileResponse> saveProfile(
            @Valid @RequestBody UserProfileRequest request,
            Principal principal) {
        return ResponseEntity.ok(userProfileService.saveProfile(principal.getName(), request));
    }

    @GetMapping
    @Operation(summary = "Dohvaćanje profila trenutnog korisnika")
    public ResponseEntity<UserProfileResponse> getProfile(Principal principal) {
        return ResponseEntity.ok(userProfileService.getProfile(principal.getName()));
    }
}