package com.careersphere.backend.controller;

// GRASP: Controller – Handles application requests.
// Pattern: MVC Controller.

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.careersphere.backend.model.Application;
import com.careersphere.backend.service.ApplicationService;

@Controller
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    // Student apply job
    @PostMapping("/apply")
    public String apply(@RequestParam String studentName,
                        @RequestParam String jobTitle) {

        Application app = new Application();
        app.setStudentName(studentName);
        app.setJobTitle(jobTitle);

        service.apply(app);
        return "redirect:/profile";
    }

    // Recruiter view applications
    @GetMapping("/applications")
    public String viewApplications(Model model) {
        List<Application> apps = service.getAll();
        model.addAttribute("apps", apps);
        return "applications";
    }

    // Approve
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        service.updateStatus(id, "APPROVED");
        return "redirect:/applications";
    }

    // Reject
    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {
        service.updateStatus(id, "REJECTED");
        return "redirect:/applications";
    }
}