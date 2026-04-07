package com.careersphere.backend.model;

// GRASP: Information Expert – This class manages its own data and operations.
// Pattern: Part of MVC Model layer representing academic profile entity.

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AcademicProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private double cgpa;
    private String skills;
    private String certifications;
    private String projects;

    // GRASP: Information Expert
    public void updateProfile(String fullName, double cgpa, String skills, String certifications, String projects) {
        this.fullName = fullName;
        this.cgpa = cgpa;
        this.skills = skills;
        this.certifications = certifications;
        this.projects = projects;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getCertifications() { return certifications; }
    public void setCertifications(String certifications) { this.certifications = certifications; }

    public String getProjects() { return projects; }
    public void setProjects(String projects) { this.projects = projects; }
}