package com.example.backend.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.backend.config.InMemoryRequestRepository;
import com.example.backend.config.TokenFilter;
import com.example.backend.config.TokenStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper mapper;
    private final TokenStore tokenStore;
    private final TokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:8081"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                config.setAllowedHeaders(List.of("*"));
                return config;
            };
            c.configurationSource(source);
        }).csrf().disable().authorizeRequests().antMatchers("/oauth2/**", "/login**").permitAll().anyRequest()
                .authenticated().and().oauth2Login().authorizationEndpoint()
                .authorizationRequestRepository(new InMemoryRequestRepository()).and()
                .successHandler(this::successHandler).and().exceptionHandling()
                .authenticationEntryPoint(this::authenticationEntryPoint).and()
                .logout(cust -> cust.addLogoutHandler(this::logout).logoutSuccessHandler(this::onLogoutSuccess));
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // You can process token here
        System.out.println("Auth token is - " + request.getHeader("Authorization"));
    }

    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // this code is just sending the 200 ok response and preventing redirect
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String token = tokenStore.generateToken(authentication);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("accessToken", token)));
    }

    private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("error", "Unauthenticated")));
    }

}
