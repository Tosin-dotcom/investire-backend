package com.tosin.investire.security.service;

import com.tosin.investire.commons.Constants;
import com.tosin.investire.security.dto.AuthUserDetails;
import com.tosin.investire.security.dto.TokenDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.tosin.investire.commons.Constants.AUTH_TOKEN_COOKIE;


@RequiredArgsConstructor
@Service
public class SecurityService {

    private final ModelMapper modelMapper;
    private final HttpServletRequest request;

    @Value("${investire.secret-key}")
    public String secretKey;


    public String generateToken(TokenDetails tokenDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.USER, tokenDetails);
        return Jwts.builder()
                .claims(claims)
                .subject(tokenDetails.getEmail())
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

    public TokenDetails getUserDetails() {

        String authToken = getAuthToken();
        return extractTokenDetails(authToken);
    }

    public UserDetails extractUserDetails(String token) {

        TokenDetails tokenDetails = extractTokenDetails(token);
        return AuthUserDetails.builder()
                .username(tokenDetails.getEmail())
                .build();
    }

    public String getAuthToken() {

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (AUTH_TOKEN_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
