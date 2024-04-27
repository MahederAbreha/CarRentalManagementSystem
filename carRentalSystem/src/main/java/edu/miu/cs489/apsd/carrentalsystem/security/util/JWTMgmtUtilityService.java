package edu.miu.cs489.apsd.carrentalsystem.security.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.function.Function;


@Service
public class JWTMgmtUtilityService {
    public static final String SECRET = "ThisIsAVeryTopLevelSecureTokenInTheWholeWideWorld";

    public String createToken(Map<String, Object> claims, String subject) {
        var now = Instant.now();
        return Jwts.builder()
                .claims()
                .subject(subject)
                .add(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(24, ChronoUnit.HOURS)))
                .and()
                .signWith(getSignKey())
                .compact();
    }
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
//    public String extractToken(String token) {
//        return token;
//    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
          return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private SecretKey getSignKey() {
        byte [] keys = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keys);
    }


}
