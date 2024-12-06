package com.example.school_portal.component;

import com.example.school_portal.service.SessionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.postgresql.util.PGobject;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionAuthenticationFilter implements Filter {

    private final SessionService sessionService;

    public SessionAuthenticationFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String sessionId = getSessionId(httpRequest);

        if (sessionId != null) {
            Map<String, Object> sessionAttributes = sessionService.getSessionAttributes(sessionId);
            PGobject userData = (PGobject) sessionAttributes.get("attribute_bytes");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(userData.getValue(), new TypeReference<>() {});

            User user = getUser(jsonMap);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), "", user.getAuthorities());

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
        }
        filterChain.doFilter(request, response);
    }

    private String getSessionId(HttpServletRequest request) {
        return (request.getSession() != null) ? request.getSession().getId() : null;
    }

    public User getUser(Map<String, Object> jsonMap) {
        String username = (String) jsonMap.get("username");

        List<Map<String, String>> authoritiesList = (List<Map<String, String>>) ((List<?>) jsonMap.get("authorities")).get(1);
        Set<SimpleGrantedAuthority> authorities = authoritiesList.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.get("authority")))
                .collect(Collectors.toSet());

        return new User(username, "", authorities);
    }
}