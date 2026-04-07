package com.careersphere.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.careersphere.backend.model.AcademicProfile;
import com.careersphere.backend.service.AcademicProfileService;

@Controller
public class ProfileViewController {

    private final AcademicProfileService service;

    public ProfileViewController(AcademicProfileService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public String showForm() {
        return "profile";
    }

    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute AcademicProfile profile) {
        service.saveProfile(profile);
        return "redirect:/profile";
    }
}