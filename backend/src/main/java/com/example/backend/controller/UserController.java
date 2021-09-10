package com.example.backend.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import com.example.backend.entities.User;
import com.example.backend.entities.UserPrincipal;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.CurrentUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
