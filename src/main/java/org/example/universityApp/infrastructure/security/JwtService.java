package org.example.universityApp.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.example.universityApp.application.authentication.TokenService;
import org.example.universityApp.domain.models.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService implements TokenService {

    @Value("${security.jwt.secret-key}")
    private String secret;

    @Getter
    @Value("${security.jwt.expiration-date}")
    private Long expirationDate;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getUsernameFromToken(
            String token
    ) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        final Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }

    // Empty claims, as there won't be role-based authorization in this test application.
    @Override
    public String generateToken(
            User user
    ) {
        return buildToken(new HashMap<>(), user, expirationDate);
    }

    @Override
    public boolean isTokenValid(
            String token,
            User user
    ) {
        try {
            String username = getUsernameFromToken(token);
            return username.equals(user.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private String buildToken(
            Map<String, Object> claims,
            User user,
            Long expirationDate
    ) {
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationDate))
                .signWith(secretKey)
                .compact();
    }

    private boolean isTokenExpired(
            String token
    ) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
