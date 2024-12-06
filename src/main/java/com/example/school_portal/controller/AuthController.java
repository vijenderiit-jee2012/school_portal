package com.example.school_portal.controller;

import ch.qos.logback.core.model.Model;
import com.example.school_portal.model.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.school_portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "login";
    }

    @PostMapping("/login-process")
    public ResponseEntity<String> login(@Valid @RequestBody Admin admin, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        String password = admin.getPassword();
        Admin loggedAdmin = authService.authenticateUser(admin.getUserName(), password);
        if (loggedAdmin != null) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getUserName(), admin.getPassword(), authorities)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User authenticatedAdmin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("currentUser", authenticatedAdmin);

            return new ResponseEntity<>("Login successful! Welcome "+ authenticatedAdmin.getUsername()+" : "+session.getId(), HttpStatus.OK);
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}
