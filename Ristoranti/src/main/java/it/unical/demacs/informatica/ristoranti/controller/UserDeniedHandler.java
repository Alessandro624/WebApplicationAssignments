package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.config.security.SecurityUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.Objects;

public class UserDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        System.err.println(
                "For user:'" + Objects.requireNonNull(SecurityUtility.getCurrentUser()).getUsername() + "' " +
                        "for resource:'" + request.getRequestURI() + "'  error:" + accessDeniedException.getMessage());
        response.getWriter().write("Access Denied. You do not have permission to access this resource.");
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}
