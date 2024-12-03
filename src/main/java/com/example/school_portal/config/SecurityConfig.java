package com.example.school_portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/login", "/signup").permitAll()  // Allow access to login/signup pages
                                .anyRequest().authenticated() // Require authentication for all other requests
                ).formLogin(
                        form -> form
                                .loginPage("/login") // Custom login page
                                .loginProcessingUrl("/login") // The URL to submit the login form to (typically the same as loginPage)
                                .defaultSuccessUrl("/home", true) // Redirect to /home after successful login
                                .failureUrl("/login?error=true") // Redirect to login page with error message after failed login
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL to trigger logout
                        .logoutSuccessUrl("/login?logout=true") // Redirect to login page after logout
                        .permitAll() // Allow anyone to logout
                );

        return http.build();
    }
}