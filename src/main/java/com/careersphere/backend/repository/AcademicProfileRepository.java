package com.careersphere.backend.repository;

// GRASP: Low Coupling – Separates database access from business logic.
// Pattern: Repository pattern for database operations.

import org.springframework.data.jpa.repository.JpaRepository;

import com.careersphere.backend.model.AcademicProfile;

public interface AcademicProfileRepository extends JpaRepository<AcademicProfile, Long> {
}