package com.careersphere.backend.service;

// GRASP: Controller – Handles job business logic.
// Pattern: Service layer in MVC.

import java.util.List;

import org.springframework.stereotype.Service;

import com.careersphere.backend.model.Job;
import com.careersphere.backend.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Job saveJob(Job job) {
        return repository.save(job);
    }

    public List<Job> getAllJobs() {
        return repository.findAll();
    }
}