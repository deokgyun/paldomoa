package com.paldomoa.auth.service;

import com.paldomoa.auth.controller.dto.request.LoginRequestDto;
import com.paldomoa.member.domain.Member;
import com.paldomoa.member.domain.repository.MemberRepository;
import com.paldomoa.member.dto.request.MemberSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long login(LoginRequestDto request) {

        String email = request.email();
        String password = request.password();

        return 1L;
    }

    public Long signUp(MemberSaveRequest memberSaveRequest) {
        boolean emailDuplication = emailDuplication(memberSaveRequest.email());

        if (emailDuplication) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member(memberSaveRequest, passwordEncoder);
        return memberRepository.save(member).getId();
    }


    private boolean emailDuplication(String email) {
        return memberRepository.existsByEmail(email);
    }

}
