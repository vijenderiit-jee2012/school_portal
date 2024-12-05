package com.example.school_portal.controller;

import com.example.school_portal.model.Admin;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.school_portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        String password = admin.getPassword();
        Admin loggedAdmin = authService.authenticateUser(admin.getUserName(), password);

        if (loggedAdmin != null) {
            System.out.println("OKAY");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loggedAdmin.getUserName(), password);

            System.out.println("OKAY_1");
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            System.out.println("OKAY_2");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("OKAY_3");
            Admin authenticatedAdmin = (Admin) authentication.getPrincipal();
            return new ResponseEntity<>("Login successful! Welcome "+ authenticatedAdmin.getName(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Login failed! Either UserName or Password is Invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/signup")
    @Validated
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup-process")
    @Validated
    public ResponseEntity<String> signupPage(@Valid @RequestBody Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        try {
            authService.registerAdmin(admin);
            return new ResponseEntity<>("Admin account created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
