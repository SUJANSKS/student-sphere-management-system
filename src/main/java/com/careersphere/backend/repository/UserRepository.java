package com.careersphere.backend.repository;

// GRASP: Low Coupling – separates DB operations from logic.
// Pattern: Repository pattern for user persistence.

import org.springframework.data.jpa.repository.JpaRepository;

import com.careersphere.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}