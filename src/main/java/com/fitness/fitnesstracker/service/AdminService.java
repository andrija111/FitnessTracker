package com.fitness.fitnesstracker.service;

import com.fitness.fitnesstracker.model.User;
import com.fitness.fitnesstracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsible for admin operations.
 * Handles user management and platform statistics.
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    /**
     * Retrieves all registered users.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Returns the total number of registered users.
     *
     * @return total user count
     */
    public long getTotalUsers() {
        return userRepository.count();
    }
}