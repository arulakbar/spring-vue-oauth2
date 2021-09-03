package com.example.backend.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/home")
@Slf4j
public class HelloController {

    @GetMapping()
    public Map<String, String> getUserName(
            @AuthenticationPrincipal(expression = "attributes['name']") String username) {
        return Collections.singletonMap("name", username);
    }
}
