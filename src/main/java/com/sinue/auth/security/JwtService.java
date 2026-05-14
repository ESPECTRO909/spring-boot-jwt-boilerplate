package com.sinue.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey SECRET_KEY;

    public JwtService(@Value("${jwt.secret:}") String propSecret) {
        String raw;
        if (propSecret != null && !propSecret.isEmpty()) {
            raw = propSecret;
        } else {
            String env = System.getenv("JWT_SECRET");
            if (env != null && !env.isEmpty()) {
                raw = env;
            } else {
                raw = "mifirmajaja_clave_segura_desarrollo";
            }
        }
        byte[] keyBytes = raw.getBytes(StandardCharsets.UTF_8);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, String rol) {
        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extrae el username del token
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // Verifica que el token no esté expirado y la firma sea válida
    public boolean isTokenValid(String token) {
        try {
            return parseClaims(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}