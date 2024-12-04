package com.example.school_portal.controller;

import com.example.school_portal.helper.Constants;
import org.springframework.ui.Model;
import com.example.school_portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "role", required = false) String role,
                        Model model) {
        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            model.addAttribute("error", "Username, password or role cannot be empty.");
            return "login";
        }

        if (role.equals(Constants.ADMIN)){

        } else if (role.equals(Constants.STUDENT)){

        } else if (role.equals(Constants.TEACHER)){

        }

        return "";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
