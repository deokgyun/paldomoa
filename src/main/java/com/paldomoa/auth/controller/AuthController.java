package com.paldomoa.auth.controller;

import com.paldomoa.auth.controller.dto.request.LoginRequestDto;
import com.paldomoa.auth.service.AuthService;
import com.paldomoa.member.dto.request.MemberSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody MemberSaveRequest memberSaveRequest) {

        Long saveId = authService.signUp(memberSaveRequest);
        return ResponseEntity.ok().body(saveId);
    }
}
