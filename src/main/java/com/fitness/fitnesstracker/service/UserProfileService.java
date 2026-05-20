package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.dto.UserProfileRequest;
import com.fitness.fitnesstracker.dto.UserProfileResponse;
import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.model.UserProfile;
import com.fitness.fitnesstracker.repository.UserProfileRepository;
import com.fitness.fitnesstracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for managing user fitness profiles.
 * Handles creating, updating and retrieving user profile data.
 */
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    /**
     * Creates or updates the fitness profile for the authenticated user.
     * If a profile already exists for the user, it will be updated.
     * If not, a new profile will be created.
     *
     * @param email   the email of the authenticated user
     * @param request contains all fitness-related profile data
     * @return the saved or updated user profile as a response DTO
     * @throws RuntimeException if the user is not found
     */
    public UserProfileResponse saveProfile(String email, UserProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElse(new UserProfile());

        profile.setUser(user);
        profile.setAge(request.getAge());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());
        profile.setGoal(request.getGoal());
        profile.setExperienceLevel(request.getExperienceLevel());
        profile.setEquipment(request.getEquipment());
        profile.setInjuries(request.getInjuries());

        userProfileRepository.save(profile);

        return mapToResponse(user, profile);
    }

    /**
     * Retrieves the fitness profile of the authenticated user.
     *
     * @param email the email of the authenticated user
     * @return the user profile as a response DTO
     * @throws RuntimeException if the user or profile is not found
     */
    public UserProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profil nije pronađen!"));

        return mapToResponse(user, profile);
    }

    /**
     * Maps User and UserProfile entities to a UserProfileResponse DTO.
     *
     * @param user    the User entity
     * @param profile the UserProfile entity
     * @return populated UserProfileResponse DTO
     */
    private UserProfileResponse mapToResponse(User user, UserProfile profile) {
        UserProfileResponse response = new UserProfileResponse();
        response.setId(profile.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setAge(profile.getAge());
        response.setHeight(profile.getHeight());
        response.setWeight(profile.getWeight());
        response.setGoal(profile.getGoal());
        response.setExperienceLevel(profile.getExperienceLevel());
        response.setEquipment(profile.getEquipment());
        response.setInjuries(profile.getInjuries());
        return response;
    }
}