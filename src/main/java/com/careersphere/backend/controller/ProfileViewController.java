package com.careersphere.backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.careersphere.backend.model.AcademicProfile;
import com.careersphere.backend.model.Job;
import com.careersphere.backend.model.User;
import com.careersphere.backend.model.Application;
import com.careersphere.backend.service.AcademicProfileService;
import com.careersphere.backend.service.JobService;
import com.careersphere.backend.service.ApplicationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileViewController {

    private final AcademicProfileService service;
    private final JobService jobService;
    private final ApplicationService applicationService;

    // ✅ Correct constructor
    public ProfileViewController(AcademicProfileService service,
                                 JobService jobService,
                                 ApplicationService applicationService) {
        this.service = service;
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

   @GetMapping("/profile")
public String showForm(Model model, HttpSession session) {

    User user = (User) session.getAttribute("user");

    // ✅ SAFE check FIRST
    if (user == null || !user.getRole().equals("STUDENT")) {
        return "redirect:/login";
    }

    // ✅ NOW safe to use user
    System.out.println("SESSION USER NAME = " + user.getName());

    // Load jobs
    List<Job> jobs = jobService.getAllJobs();
    model.addAttribute("jobs", jobs);

    // Load applications
    List<Application> apps =
            applicationService.getByStudentName(user.getName());
    model.addAttribute("apps", apps);

    return "profile";
}

    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute AcademicProfile profile) {
        service.saveProfile(profile);
        return "redirect:/profile";
    }
}