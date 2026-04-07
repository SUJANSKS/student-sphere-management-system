package com.careersphere.backend.service;

// GRASP: Low Coupling – Separates database access from business logic.
// Pattern: Repository pattern for database operations.

import java.util.List;

import org.springframework.stereotype.Service;

import com.careersphere.backend.model.AcademicProfile;
import com.careersphere.backend.repository.AcademicProfileRepository;

@Service
public class AcademicProfileService {

    private final AcademicProfileRepository repository;

    public AcademicProfileService(AcademicProfileRepository repository) {
        this.repository = repository;
    }

    public AcademicProfile saveProfile(AcademicProfile profile) {
        return repository.save(profile);
    }

    public List<AcademicProfile> getAllProfiles() {
        return repository.findAll();
    }
}