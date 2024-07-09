package com.paldomoa.auth.jwt.service;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    private SecretKey secretKey;

    private static final String ACCESS_TOKEN = "AccessToken";
    private static final String REFRESH_TOKEN = "RefreshToken";
    private static final String BEARER = "Bearer ";

    @Value("${spring.jwt.access.expiration}")
    private long accessTokenValidityInSeconds;

    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenValidityInSeconds;

    @Value("${spring.jwt.access.header}")
    private String accessHeader;

    @Value("${spring.jwt.refresh.header}")
    private String refreshHeader;


    public JwtService(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
            Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("id", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .getExpiration().before(new Date());
    }


    public String createAccessToken(Long memberId, String role) {
        return Jwts.builder()
            .subject(ACCESS_TOKEN)
            .claim("id", memberId)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + accessTokenValidityInSeconds))
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
            .subject(REFRESH_TOKEN)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + refreshTokenValidityInSeconds))
            .signWith(secretKey)
            .compact();
    }

    public void sendAccessTokenAndRefreshToken(HttpServletResponse response, String accessToken,
        String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, BEARER + accessToken);
        setRefreshTokenHeader(response, BEARER + refreshToken);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put(ACCESS_TOKEN, accessToken);
        tokenMap.put(REFRESH_TOKEN, refreshToken);

    }

    private void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.addHeader(accessHeader, accessToken);
    }

    private void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.addHeader(refreshHeader, refreshToken);

//        Cookie cookie = new Cookie(key, refreshToken);
//        cookie.setMaxAge(24 * 60 * 60);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//
//        response.addCookie(cookie);
    }

}
