package com.paldomoa.auth.login;

import com.paldomoa.auth.annotation.Member;
import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.ExceptionData;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginMemberResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        return Optional.ofNullable(Objects.requireNonNull(request).getAttribute("payload"))
            .orElseThrow(() -> new ApiException(ExceptionData.RUNTIME_EXCEPTION));
    }
}
