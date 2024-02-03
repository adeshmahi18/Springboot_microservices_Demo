package com.gateway.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret}")
    private String secret;

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}
