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
    // @Value("${security.jwt.token.secret-key:secret-key}")
    // private String secretKey;
    @Autowired
    private AppConfig appConfig;

    // @PostConstruct
    // protected void init() {
    // // this is to avoid having the raw secret key available in the JVM
    // secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    // }

    public String createToken(Authentication authentication) {

        log.info("Authentication is {}", authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        log.info("USER PRINCIPAL IS {}", userPrincipal.getEmail());

        Date now = new Date();

        Date validity = new Date(now.getTime() + 3600000);

        String token = Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(appConfig.getTokenSecret().getBytes()), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Long getUserIdFromToken(String token) {
        // Claims claims =
        // Jwts.parser().setSigningKey(appConfig.getTokenSecret()).parseClaimsJws(token).getBody();
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(appConfig.getTokenSecret().getBytes()))
                .build().parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            // Jwts.parser().setSigningKey(appConfig.getTokenSecret()).parseClaimsJws(authToken);
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(appConfig.getTokenSecret().getBytes())).build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
