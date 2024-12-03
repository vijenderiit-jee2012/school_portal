package com.example.school_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// pg_ctl.exe restart -D  "C:\Program Files\PostgreSQL\17\data"
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SchoolPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolPortalApplication.class, args);
    }
}
