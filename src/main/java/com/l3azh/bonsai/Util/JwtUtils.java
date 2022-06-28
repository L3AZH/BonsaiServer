package com.l3azh.bonsai.Util;

import com.l3azh.bonsai.Config.UserDetailImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.credential}")
    private String jwtCredential;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateJwToken(Authentication authentication) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtCredential)
                .compact();
    }

    public Claims getClaimFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtCredential).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(Claims token) {
        return token.getExpiration().after(new Date());
    }

    public String getEmailFromJwt(String token) {
        Claims claims = getClaimFromJwt(token);
        if (claims != null && isTokenExpired(claims)) {
            return claims.getSubject();
        }
        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtCredential).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}