package com.careersphere.backend.controller;

// GRASP: Controller – Handles job-related requests.
// Pattern: MVC Controller for job operations.

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.careersphere.backend.model.Job;
import com.careersphere.backend.service.JobService;

@Controller
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    // Show recruiter dashboard
    @GetMapping("/recruiter")
    public String recruiterDashboard(Model model) {
        List<Job> jobs = service.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "recruiter";
    }

    // Show job form
    @GetMapping("/job/new")
    public String jobForm() {
        return "job_form";
    }

    // Save job
    @PostMapping("/job/save")
    public String saveJob(@ModelAttribute Job job) {
        service.saveJob(job);
        return "redirect:/recruiter";
    }
}