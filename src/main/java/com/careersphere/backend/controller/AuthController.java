package com.careersphere.backend.controller;

// GRASP: Controller – Handles authentication requests
// Pattern: MVC Controller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.careersphere.backend.model.User;
import com.careersphere.backend.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    //  Show register page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    //  Handle register
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {

        if (user.getRole() != null) {
            user.setRole(user.getRole().trim().toUpperCase());
        }

        service.register(user);
        return "redirect:/login";
    }

    //  Show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    //  Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        if (user == null) {
            return "login";
        }

        //  Store session
        session.setAttribute("user", user);

        String role = user.getRole().trim().toUpperCase();

        if (role.equals("STUDENT")) {
            return "redirect:/profile";
        } 
        else if (role.equals("RECRUITER")) {
            return "redirect:/recruiter";
        } 
        else if (role.equals("ADMIN")) {
            return "redirect:/admin";
        }

        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}