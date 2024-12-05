package com.example.school_portal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/session-info")
    public String getSessionInfo(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Session ID: " + session.getId() + ", User: " + username;
    }

    @GetMapping("/set-session-data")
    public String setSessionData(HttpSession session) {
        session.setAttribute("userRole", "ADMIN");  // Set a custom session attribute
        return "Session data (userRole) set!";
    }

    @GetMapping("/get-session-data")
    public String getSessionData(HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");  // Retrieve custom session attribute
        return "User role from session: " + userRole;
    }
}
