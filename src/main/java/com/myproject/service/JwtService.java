package com.myproject.service;

import com.myproject.cbts.models.CbtsUser;
import com.myproject.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long JWT_EXPIRY_TIME = 1000 * 60 * 30;
//    private final String secreteKey= Base64.getEncoder()
//            .encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());

    private Key getKey() {
        log.info("secretKey: " + secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String generateToken(Users user) {
        Map<String, Object> claims = new HashMap();
        claims.put("role", user.getRole());
        return createToken(claims, user.getUsername());
    }
    //This for only CBTS Token
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String generateTokenForCbtsUser(CbtsUser user) {
        Map<String, Object> claims = new HashMap();
        claims.put("roles",user.getRoles() .stream().map(role -> role.getRoleName()).toList());
        return createToken(claims, user.getUsername());
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRY_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, Users user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
