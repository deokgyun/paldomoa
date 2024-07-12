package com.paldomoa.auth.service;

import com.paldomoa.auth.domain.CustomUserDetails;
import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.ExceptionData;
import com.paldomoa.member.domain.Member;
import com.paldomoa.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_MEMBER_ID));

        return new CustomUserDetails(member);

    }
}