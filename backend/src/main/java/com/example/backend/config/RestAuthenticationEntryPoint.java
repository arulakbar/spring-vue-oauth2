package com.example.backend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException aException)
            throws IOException, ServletException {
        log.error("Responding with unauthorized error. Message - {}", aException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, aException.getLocalizedMessage());
    }

}
