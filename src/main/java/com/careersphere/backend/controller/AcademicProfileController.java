package com.careersphere.backend.controller;

// GRASP: Controller – Handles incoming requests and delegates to service.
// Pattern: MVC Controller managing REST APIs.

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careersphere.backend.model.AcademicProfile;
import com.careersphere.backend.service.AcademicProfileService;

@RestController
@RequestMapping("/api/profile")
public class AcademicProfileController {

    private final AcademicProfileService service;

    public AcademicProfileController(AcademicProfileService service) {
        this.service = service;
    }

    @PostMapping
    public AcademicProfile createProfile(@RequestBody AcademicProfile profile) {
        return service.saveProfile(profile);
    }

    @GetMapping
    public List<AcademicProfile> getProfiles() {
        return service.getAllProfiles();
    }
}