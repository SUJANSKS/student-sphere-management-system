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
import com.careersphere.backend.service.AcademicProfileService;
import com.careersphere.backend.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileViewController {

    private final AcademicProfileService service;
    private final JobService jobService;

    public ProfileViewController(AcademicProfileService service,
                                 JobService jobService) {
        this.service = service;
        this.jobService = jobService;
    }

    @GetMapping("/profile")
    public String showForm(Model model, HttpSession session) {

        //  Get logged-in user
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("STUDENT")) {
            return "redirect:/login";
        }

        //  Load jobs
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);

        return "profile";
    }

    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute AcademicProfile profile) {
        service.saveProfile(profile);
        return "redirect:/profile";
    }
}