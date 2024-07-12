package com.paldomoa.auth.login;

import static com.paldomoa.common.exception.ExceptionData.INVALID_REQUEST;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.paldomoa.auth.annotation.Member;
import com.paldomoa.auth.domain.Accessor;
import com.paldomoa.auth.jwt.service.JwtProvider;
import com.paldomoa.auth.login.domain.MemberTokens;
import com.paldomoa.auth.login.domain.repository.RefreshTokenRepository;
import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.BadRequestException;
import com.paldomoa.common.exception.ExceptionData;
import com.paldomoa.common.exception.RefreshTokenException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoginMemberResolver implements HandlerMethodArgumentResolver {

    private static final String REFRESH_TOKEN = "refresh_token";

    private final RefreshTokenRepository refreshTokenRepository;

    private final BearerAuthorizationExtractor extractor;
    private final JwtProvider jwtProvider;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(Member.class);
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory) {

        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            throw new BadRequestException(INVALID_REQUEST);
        }

        try {
            String refreshToken = extractRefreshToken(request.getCookies());
            String accessToken = extractor.extractAccessToken(webRequest.getHeader(AUTHORIZATION));
            jwtProvider.validateTokens(new MemberTokens(refreshToken, accessToken));
            
            Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));

            return Accessor.member(memberId);
        } catch (ApiException e) {
            return Accessor.guest();
        }
    }

    private String extractRefreshToken(Cookie... cookies) {
        if (cookies == null || cookies.length == 0) {
            throw new ApiException(ExceptionData.NOT_FOUND_REFRESH_TOKEN);
        }
        return Arrays.stream(cookies)
            .filter(this::isValidRefreshToken)
            .findFirst()
            .orElseThrow(() -> new RefreshTokenException(ExceptionData.NOT_FOUND_REFRESH_TOKEN))
            .getValue();
    }

    private boolean isValidRefreshToken(Cookie cookie) {
        return REFRESH_TOKEN.equals(cookie.getName()) && refreshTokenRepository.existsById(
            cookie.getValue());
    }
}
