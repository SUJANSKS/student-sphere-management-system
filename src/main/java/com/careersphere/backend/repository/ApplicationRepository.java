package com.careersphere.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careersphere.backend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // ✅ existing
    List<Application> findByStudentName(String studentName);

    // ✅ ADD THIS (INSIDE interface)
    boolean existsByStudentNameAndJobTitle(String studentName, String jobTitle);
}