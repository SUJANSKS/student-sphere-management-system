package com.careersphere.backend.repository;

// GRASP: Low Coupling – separates DB operations.
// Pattern: Repository pattern for Job.

import org.springframework.data.jpa.repository.JpaRepository;

import com.careersphere.backend.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}