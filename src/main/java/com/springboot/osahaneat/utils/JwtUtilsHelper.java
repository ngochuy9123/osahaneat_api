package com.springboot.osahaneat.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilsHelper {

    @Value("${jwt.privatekey}")
    private String privatekey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privatekey));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }

    public boolean verifyToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privatekey));
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
