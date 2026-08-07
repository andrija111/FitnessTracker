package com.fitness.fitnesstracker.repository;

import com.fitness.fitnesstracker.model.WorkoutProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for WorkoutProgress entity.
 * Provides standard CRUD operations and a custom method
 * for retrieving all progress entries for a specific user.
 */
@Repository
public interface WorkoutProgressRepository extends JpaRepository<WorkoutProgress, Long> {

    /**
     * Finds all progress entries for a specific user ordered by date descending.
     *
     * @param userId the ID of the user
     * @return list of progress entries
     */
    List<WorkoutProgress> findByUserIdOrderByDateDesc(Long userId);
}