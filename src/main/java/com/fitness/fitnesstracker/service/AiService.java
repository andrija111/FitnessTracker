package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.model.UserProfile;
import com.fitness.fitnesstracker.model.WorkoutPlan;
import com.fitness.fitnesstracker.repository.UserProfileRepository;
import com.fitness.fitnesstracker.repository.UserRepository;
import com.fitness.fitnesstracker.repository.WorkoutPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for generating AI-powered personalized workout plans.
 * Communicates with the Groq API to generate plans based on user profile data.
 */
@Service
@RequiredArgsConstructor
public class AiService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODEL = "llama-3.3-70b-versatile";

    /**
     * Generates a personalized workout plan for the authenticated user.
     * Fetches the user profile, builds a prompt and sends it to the Groq API.
     * The generated plan is saved to the database and returned.
     *
     * @param email the email of the authenticated user
     * @return the AI-generated workout plan as a String
     * @throws RuntimeException if the user or profile is not found
     */
    public String generateWorkoutPlan(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profil nije pronađen! Prvo popuni profil."));

        String prompt = buildPrompt(profile);
        String generatedPlan = callGroqApi(prompt);

        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setUser(user);
        workoutPlan.setContent(generatedPlan);
        workoutPlan.setCreatedAt(LocalDateTime.now());
        workoutPlanRepository.save(workoutPlan);

        return generatedPlan;
    }

    /**
     * Retrieves all previously generated workout plans for the authenticated user.
     *
     * @param email the email of the authenticated user
     * @return list of workout plans
     */
    public List<WorkoutPlan> getWorkoutPlans(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen!"));

        return workoutPlanRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }

    /**
     * Builds a personalized prompt based on the user's fitness profile.
     *
     * @param profile the user's fitness profile
     * @return formatted prompt string to send to the AI
     */
    private String buildPrompt(UserProfile profile) {
        return String.format("""
                Generate a weekly workout plan for a user with the following profile:
                - Age: %d years
                - Height: %.1f cm
                - Weight: %.1f kg
                - Fitness goal: %s
                - Experience level: %s
                - Available equipment: %s
                - Injuries or limitations: %s
                
                IMPORTANT: Avoid any exercises that could worsen the listed injuries.
                Suggest safe alternatives where needed.
                Structure the plan by day (Monday to Sunday).
                For each exercise include: name, sets, reps and rest time.
                """,
                profile.getAge(),
                profile.getHeight(),
                profile.getWeight(),
                profile.getGoal(),
                profile.getExperienceLevel(),
                profile.getEquipment(),
                profile.getInjuries() != null ? profile.getInjuries() : "none"
        );
    }

    /**
     * Sends the prompt to the Groq API and returns the generated response.
     *
     * @param prompt the formatted prompt to send
     * @return the AI-generated text response
     */
    private String callGroqApi(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(groqApiKey);

        Map<String, Object> body = Map.of(
                "model", MODEL,
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_API_URL, entity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
    }
}