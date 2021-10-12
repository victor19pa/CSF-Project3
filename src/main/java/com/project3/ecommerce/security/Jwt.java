package com.project3.ecommerce.security;

import com.project3.ecommerce.models.Administrator;
import io.jsonwebtoken.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.project3.ecommerce.security.SecurityConstants.*;

public class Jwt {
    public static String createToken(Administrator administrator) {

        String jwt = Jwts.builder()
                .setSubject(administrator.getEmail())
                .claim("email", administrator.getEmail())
                .claim("password",administrator.getJwt())
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
        return jwt;

    }

    public static boolean isThisAValidateToken(String newToken, String oldToken) {
        return newToken.equals(oldToken);
    }

    public static String createTokenCode(String code) {

        String jwt = Jwts.builder()
                .claim("code", code)
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
        return jwt;

    }
}
