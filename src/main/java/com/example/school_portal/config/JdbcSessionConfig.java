package com.example.school_portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;


@Configuration
@EnableJdbcHttpSession // Enables JDBC-based session storage
public class JdbcSessionConfig {
}