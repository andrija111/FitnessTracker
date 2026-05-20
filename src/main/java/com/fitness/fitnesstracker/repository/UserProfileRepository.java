package com.fitness.fitnesstracker.repository;

import com.fitness.fitnesstracker.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for UserProfile entity.
 * Provides standard CRUD operations inherited from JpaRepository,
 * as well as a custom method for finding a profile by user ID.
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /**
     * Finds a user profile by the associated user's ID.
     * Spring Data JPA automatically generates the query based on the method name.
     *
     * @param userId the ID of the user
     * @return an Optional containing the UserProfile if found, or empty if not
     */
    Optional<UserProfile> findByUserId(Long userId);
}