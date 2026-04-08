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
import com.careersphere.backend.model.User;
import com.careersphere.backend.service.ApplicationService;
import com.careersphere.backend.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JobController {

    private final JobService service;
    private final ApplicationService applicationService;

    public JobController(JobService service,
                         ApplicationService applicationService) {
        this.service = service;
        this.applicationService = applicationService;
    }

    //  Recruiter Dashboard
    @GetMapping("/recruiter")
    public String recruiterDashboard(Model model, HttpSession session) {

        //  Session check
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("RECRUITER")) {
            return "redirect:/login";
        }

        //  Load jobs
        List<Job> jobs = service.getAllJobs();
        model.addAttribute("jobs", jobs);

        //  Load applications
        model.addAttribute("apps", applicationService.getAll());

        return "recruiter";
    }

    //  Show Job Form
    @GetMapping("/job/new")
    public String jobForm(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("RECRUITER")) {
            return "redirect:/login";
        }

        return "job_form";
    }

    //  Save Job
    @PostMapping("/job/save")
    public String saveJob(@ModelAttribute Job job, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("RECRUITER")) {
            return "redirect:/login";
        }

        service.saveJob(job);

        return "redirect:/recruiter";
    }
}