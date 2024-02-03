package com.serviceApp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "drrghdfgvdhjsjsjhshywbbsbsbghwjnsbsxvvsvsbbsbbvsvvsvvsv";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        //System.out.println("claims1==="+claims);
        //System.out.println("userName1==="+userName);
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        //System.out.println("subject==="+subject);

      //  System.out.println("claims==="+claims);
       String token= Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key()).compact();
       // System.out.println("token==="+token);
        return token;
    }

    private Key key(){
       // System.out.println("subject==="+SECRET_KEY);
        return Keys.hmacShaKeyFor(
                 Decoders.BASE64.decode(SECRET_KEY)
        );
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
