package com.paldomoa.admin.service;

import com.paldomoa.admin.domain.AdminRepository;
import com.paldomoa.member.domain.Member;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Member> getMember() {
        return adminRepository.findAll();
    }

}
