package com.fitness.fitnesstracker.repository;

import com.fitness.fitnesstracker.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for WorkoutPlan entity.
 * Provides standard CRUD operations and a custom method
 * for retrieving all workout plans for a specific user.
 */
@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {

    /**
     * Finds all workout plans for a specific user ordered by creation date descending.
     *
     * @param userId the ID of the user
     * @return list of workout plans for the user
     */
    List<WorkoutPlan> findByUserIdOrderByCreatedAtDesc(Long userId);
}