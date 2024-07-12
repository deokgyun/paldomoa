package com.paldomoa.auth.jwt.service;

import static com.paldomoa.common.exception.ExceptionData.EXPIRED_PERIOD_ACCESS_TOKEN;
import static com.paldomoa.common.exception.ExceptionData.EXPIRED_PERIOD_REFRESH_TOKEN;
import static com.paldomoa.common.exception.ExceptionData.INVALID_ACCESS_TOKEN;
import static com.paldomoa.common.exception.ExceptionData.INVALID_REFRESH_TOKEN;

import com.paldomoa.auth.login.domain.MemberTokens;
import com.paldomoa.common.exception.ExpiredJwtException;
import com.paldomoa.common.exception.ExpiredPeriodJwtException;
import com.paldomoa.common.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public static final String EMPTY_SUBJECT = "";

    private final SecretKey secretKey;
    private final Long accessExpirationTime;
    private final Long refreshExpirationTime;

    public JwtProvider(
        @Value("${spring.jwt.secret}") final String secretKey,
        @Value("${spring.jwt.access.expiration}") final Long accessExpirationTime,
        @Value("${spring.jwt.refresh.expiration}") final Long refreshExpirationTime
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public MemberTokens generateLoginToken(final String subject) {
        final String refreshToken = createToken(EMPTY_SUBJECT, refreshExpirationTime);
        final String accessToken = createToken(subject, accessExpirationTime);
        return new MemberTokens(refreshToken, accessToken);
    }

    private String createToken(final String subject, final Long validityInMilliseconds) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public void validateTokens(final MemberTokens memberTokens) {
        validateRefreshToken(memberTokens.refreshToken());
        validateAccessToken(memberTokens.accessToken());
    }

    private void validateRefreshToken(final String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException(EXPIRED_PERIOD_REFRESH_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException(INVALID_REFRESH_TOKEN);
        }
    }

    private void validateAccessToken(final String accessToken) {
        try {
            parseToken(accessToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException(EXPIRED_PERIOD_ACCESS_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException(INVALID_ACCESS_TOKEN);
        }
    }

    public String getSubject(final String token) {
        return parseToken(token)
            .getBody()
            .getSubject();
    }

    private Jws<Claims> parseToken(final String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
    }

    public boolean isValidRefreshAndInvalidAccess(final String refreshToken,
        final String accessToken) {
        validateRefreshToken(refreshToken);
        try {
            validateAccessToken(accessToken);
        } catch (final ExpiredPeriodJwtException e) {
            return true;
        }
        return false;
    }

    public String regenerateAccessToken(final String subject) {
        return createToken(subject, accessExpirationTime);
    }

    public boolean isValidRefreshAndValidAccess(final String refreshToken,
        final String accessToken) {
        try {
            validateRefreshToken(refreshToken);
            validateAccessToken(accessToken);
            return true;
        } catch (final JwtException e) {
            return false;
        }
    }

}
