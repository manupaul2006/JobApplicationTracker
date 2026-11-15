package com.manu.jobapplicationtracker.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
@Component
public class JwtUtils {
    private final String jwtSecret;
    private final int jwtExpiration;

    JwtUtils(
         @Value("${JWT.key}") String jwtSecret,
         @Value("${JWT.EXPIRATION}") int jwtExpiration
    ){
        this.jwtExpiration = jwtExpiration;
        this.jwtSecret = jwtSecret;
    }

    public String getSubject(String jwt) {
        return getClaims(jwt).getSubject();
    }

    public String getJwt(String id) {
        return Jwts
                .builder()
                .claims(new HashMap<>())
                .signWith(getSigningKey())
                .header().empty().add("typ", "JJWT")
                .and()
                .subject(id)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (jwtExpiration * 60 * 1000L)))
                .compact();
    }

    private Claims getClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

}
