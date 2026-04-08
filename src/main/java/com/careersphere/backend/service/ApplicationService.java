package com.careersphere.backend.service;

// GRASP: Controller – Handles application logic.
// Pattern: Service layer.

import java.util.List;

import org.springframework.stereotype.Service;

import com.careersphere.backend.model.Application;
import com.careersphere.backend.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public Application apply(Application app) {
        app.setStatus("PENDING");
        return repository.save(app);
    }

    public List<Application> getAll() {
        return repository.findAll();
    }

    public void updateStatus(Long id, String status) {
        Application app = repository.findById(id).orElse(null);
        if (app != null) {
            app.setStatus(status);
            repository.save(app);
        }
    }
}