package com.example.backend.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private final Map<String, OAuth2AuthorizationRequest> cache = new HashMap<>();

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        String state = request.getParameter("state");

        log.info("Load State is {}", state);

        if (state != null) {
            return removeAuthorizationRequest(request);
        }
        return null;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request,
            HttpServletResponse response) {
        String state = authorizationRequest.getState();

        log.info("Save state is {}", state);

        cache.put(state, authorizationRequest);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        String state = request.getParameter("state");

        log.info("Remove state is {}", state);

        if (state != null) {
            return cache.remove(state);
        }

        return null;
    }

}
