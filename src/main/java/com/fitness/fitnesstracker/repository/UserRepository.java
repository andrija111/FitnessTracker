package com.fitness.fitnesstracker.repository;

import com.fitness.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides standard CRUD operations inherited from JpaRepository,
 * as well as a custom method for finding a user by email address.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * Spring Data JPA automatically generates the query based on the method name.
     *
     * @param email the email address of the user
     * @return an Optional containing the User if found, or empty if not
     */
    Optional<User> findByEmail(String email);
}