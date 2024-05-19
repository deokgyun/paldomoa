package com.paldomoa.admin.controller;

import com.paldomoa.admin.service.AdminService;
import com.paldomoa.member.domain.Member;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getCurrentUser() {
        return ResponseEntity.ok().body(adminService.getMember());
    }
    
}
