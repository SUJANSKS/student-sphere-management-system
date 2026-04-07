package com.careersphere.backend.controller;

// GRASP: Controller – Handles authentication requests.
// Pattern: MVC Controller for login and registration.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.careersphere.backend.model.User;
import com.careersphere.backend.service.UserService;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // Show register page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    // Save user
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        service.register(user);
        return "redirect:/login";
    }

    // Show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        User user = service.login(email, password);

        if (user == null) {
            return "login";
        }

        // Role-based redirect
        if (user.getRole().equals("STUDENT")) {
            return "redirect:/profile";
        } else if (user.getRole().equals("RECRUITER")) {
            return "redirect:/recruiter";
        } else {
            return "redirect:/admin";
        }
    }
}