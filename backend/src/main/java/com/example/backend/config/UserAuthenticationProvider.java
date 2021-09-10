package com.example.backend.config;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.example.backend.entities.UserPrincipal;
import com.example.backend.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserAuthenticationProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();

        Date validity = new Date(now.getTime() + 3600000);

        String token = Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(now)
                .setExpiration(validity).signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Claims getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody();
    }
}
