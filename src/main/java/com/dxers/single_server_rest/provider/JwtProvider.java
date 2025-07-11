package com.dxers.single_server_rest.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
  
    @Value("${jwt.secret}")
    private String secretKey;

    public String create(String userEmail) {

        Date expiration = Date.from(Instant.now().plus(9, ChronoUnit.HOURS));

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = null;

        try {

            jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userEmail)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .compact();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return jwt;

    }

    public String validate(String jwt) {

        String userEmail = null;

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {

        userEmail = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .getBody()
            .getSubject();

        } catch(Exception exception) {
        exception.printStackTrace();
        }

        return userEmail;

    }

}