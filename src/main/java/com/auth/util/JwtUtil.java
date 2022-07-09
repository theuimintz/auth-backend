package com.auth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {

    @Value ( "${security.token.secret}")
    private String secret;

    public String generateJwtToken(String username) throws JWTCreationException {
        return JWT.create()
            .withSubject(username)
            .withIssuer("auth0")
            .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(secret))
            .withIssuer("auth0")
            .build()
            .verify(token);
    }

}
