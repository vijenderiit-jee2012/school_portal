package com.example.school_portal.service;

import com.example.school_portal.model.Admin;
import com.example.school_portal.repository.AdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

@Service
public class SessionService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final JdbcTemplate jdbcTemplate;

    public SessionService(AdminRepository adminRepository, JdbcTemplate jdbcTemplate) {
        this.adminRepository = adminRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(admin.getUserName(), admin.getPassword(), authorities);
    }

    public Map<String, Object> getSessionData(String sessionId) {
        String query = "SELECT * FROM spring_session WHERE session_id = ?";
        return jdbcTemplate.queryForMap(query, sessionId);
    }

    public Map<String, Object> getSessionAttributes(String sessionId) {
        String query = "SELECT s.SESSION_ID, s.CREATION_TIME, s.LAST_ACCESS_TIME, s.MAX_INACTIVE_INTERVAL, sa.ATTRIBUTE_BYTES FROM SPRING_SESSION s INNER JOIN SPRING_SESSION_ATTRIBUTES sa ON s.PRIMARY_ID = sa.SESSION_PRIMARY_ID WHERE s.SESSION_ID = ? and sa.ATTRIBUTE_NAME='currentUser'";
        return jdbcTemplate.queryForMap(query, sessionId);
    }

    public boolean isSessionValid(String sessionId) {
        return true;
    }
}