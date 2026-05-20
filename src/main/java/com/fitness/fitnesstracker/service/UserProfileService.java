package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.dto.UserProfileRequest;
import com.fitness.fitnesstracker.dto.UserProfileResponse;
import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.model.UserProfile;
import com.fitness.fitnesstracker.repository.UserProfileRepository;
import com.fitness.fitnesstracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

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

    public UserProfileResponse getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profil nije pronađen!"));

        return mapToResponse(user, profile);
    }

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