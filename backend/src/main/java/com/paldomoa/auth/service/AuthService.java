package com.paldomoa.auth.service;

import com.paldomoa.auth.controller.dto.request.LoginRequestDto;
import com.paldomoa.common.api.ApiResult;
import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.ExceptionData;
import com.paldomoa.member.domain.Member;
import com.paldomoa.member.domain.repository.MemberRepository;
import com.paldomoa.member.dto.request.UserSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public Long login(LoginRequestDto request) {

        String email = request.email();
        String password = request.password();

        return 1L;
    }

    @Transactional(readOnly = false)
    public ApiResult signUp(UserSaveRequest userSaveRequest) {
        boolean emailDuplication = emailDuplication(userSaveRequest.email());

        if (emailDuplication) {
            throw new ApiException(ExceptionData.DUPLICATE_MEMBER_EMAIL);
        }

        Member member = new Member(userSaveRequest, passwordEncoder);
        memberRepository.save(member);

        return new ApiResult("success", "회원가입에 성공하였습니다.");
    }


    private boolean emailDuplication(String email) {
        return memberRepository.existsByEmail(email);
    }

}
