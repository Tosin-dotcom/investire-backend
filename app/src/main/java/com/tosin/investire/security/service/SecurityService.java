package com.tosin.investire.security.service;

import com.tosin.investire.commons.Constants;
import com.tosin.investire.security.dto.TokenDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class SecurityService {

    private final ModelMapper modelMapper;

    @Value("${investire.secret-key}")
    public String secretKey;


    private String generateToken(TokenDetails tokenDetails, String email) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.USER, tokenDetails);
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(40, ChronoUnit.HOURS)))
                .signWith(getSignKey())
                .compact();
    }


    private SecretKey getSignKey() {

        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDetails extractTokenDetails(String token) {

        Claims claims = (Claims) Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parse(token)
                .getPayload();
        return modelMapper.map(claims.get(Constants.USER, Map.class), TokenDetails.class);
    }


}
