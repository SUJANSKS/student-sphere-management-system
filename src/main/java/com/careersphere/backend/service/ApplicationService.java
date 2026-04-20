
package com.careersphere.backend.service;

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

    boolean alreadyApplied =
        repository.existsByStudentNameAndJobTitle(
            app.getStudentName(),
            app.getJobTitle()
        );

    if (alreadyApplied) {
        return null; // ❗ stop duplicate
    }

    app.setStatus("PENDING");
    return repository.save(app);
}
    public List<Application> getAll() {
        return repository.findAll();
    }

    public List<Application> getByStudentName(String name) {
        return repository.findByStudentName(name);
    }

    public void updateStatus(Long id, String status) {
        Application app = repository.findById(id).orElse(null);
        if (app != null) {
            app.setStatus(status);
            repository.save(app);
        }
    }
}