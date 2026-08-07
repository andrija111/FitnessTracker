package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.dto.WorkoutProgressRequest;
import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.model.WorkoutProgress;
import com.fitness.fitnesstracker.repository.UserRepository;
import com.fitness.fitnesstracker.repository.WorkoutProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service responsible for managing workout progress entries.
 * Handles logging and retrieving user progress data.
 */
@Service
@RequiredArgsConstructor
public class WorkoutProgressService {

    private final WorkoutProgressRepository workoutProgressRepository;
    private final UserRepository userRepository;

    /**
     * Logs a new workout progress entry for the authenticated user.
     *
     * @param email   the email of the authenticated user
     * @param request contains weight and notes
     * @return the saved progress entry
     */
    public WorkoutProgress logProgress(String email, WorkoutProgressRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        WorkoutProgress progress = new WorkoutProgress();
        progress.setUser(user);
        progress.setWeight(request.getWeight());
        progress.setNotes(request.getNotes());
        progress.setDate(LocalDate.now());

        return workoutProgressRepository.save(progress);
    }

    /**
     * Retrieves all workout progress entries for the authenticated user.
     *
     * @param email the email of the authenticated user
     * @return list of progress entries ordered by date descending
     */
    public List<WorkoutProgress> getProgress(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        return workoutProgressRepository.findByUserIdOrderByDateDesc(user.getId());
    }

    /**
     * Retrieves the last 5 progress entries as a formatted string for AI prompt.
     *
     * @param userId the ID of the user
     * @return formatted string of recent progress notes
     */
    public String getProgressSummaryForAi(Long userId) {
        List<WorkoutProgress> entries = workoutProgressRepository
                .findByUserIdOrderByDateDesc(userId);

        if (entries.isEmpty()) {
            return "No previous progress logged.";
        }

        StringBuilder sb = new StringBuilder();
        entries.stream().limit(5).forEach(p -> {
            sb.append("- Date: ").append(p.getDate())
                    .append(", Weight: ").append(p.getWeight()).append("kg")
                    .append(", Notes: ").append(p.getNotes() != null ? p.getNotes() : "none")
                    .append("\n");
        });

        return sb.toString();
    }
}