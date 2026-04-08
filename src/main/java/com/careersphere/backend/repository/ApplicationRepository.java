package com.careersphere.backend.repository;

// GRASP: Low Coupling – separates DB operations.
// Pattern: Repository pattern.



import org.springframework.data.jpa.repository.JpaRepository;

import com.careersphere.backend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


}

