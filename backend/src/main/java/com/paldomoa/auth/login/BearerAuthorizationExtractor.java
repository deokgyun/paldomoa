package com.paldomoa.auth.login;

import com.paldomoa.common.exception.ExceptionData;
import com.paldomoa.common.exception.InvalidJwtException;
import org.springframework.stereotype.Component;

@Component
public class BearerAuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public String extractAccessToken(String token) {
        if (token != null && token.startsWith(BEARER_TYPE)) {
            return token.substring(BEARER_TYPE.length()).trim();
        }
        
        throw new InvalidJwtException(ExceptionData.INVALID_ACCESS_TOKEN);
    }
}
