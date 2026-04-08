package com.careersphere.backend.controller;

// GRASP: Controller – handles admin operations
// Pattern: MVC Controller + Service Layer

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.careersphere.backend.service.ApplicationService;
import com.careersphere.backend.service.JobService;
import com.careersphere.backend.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;

    public AdminController(ApplicationService applicationService,
                           JobService jobService,
                           UserService userService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
    }

    //  ADMIN DASHBOARD
    @GetMapping
    public String adminDashboard(Model model) {

        model.addAttribute("applications", applicationService.getAll());
        model.addAttribute("jobs", jobService.getAllJobs());
        model.addAttribute("users", userService.getAllUsers());

        model.addAttribute("totalUsers", userService.getAllUsers().size());
        model.addAttribute("totalJobs", jobService.getAllJobs().size());
        model.addAttribute("totalApplications", applicationService.getAll().size());

        return "admin";
    }

    // APPROVE / REJECT APPLICATION
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Long id,
                               @RequestParam String status) {

        applicationService.updateStatus(id, status);
        return "redirect:/admin";
    }
}