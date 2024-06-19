package com.ufpso.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class JWTService {
    private static final String SECRET_KEY = "Y2xhdmUgc2VjcmV0YSBtdXkgcGVybyBtdXkgc2VndXJh";
    private static final long accessTokenValidity = 60*60*1000;//1 hour 36000 ms
    public String generateToken(UserDetails user){
        return generateToken(new HashMap<>(), user);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity)).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).
                compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            log.error("{}", e.getMessage());
            return null;
        }
    }
}
