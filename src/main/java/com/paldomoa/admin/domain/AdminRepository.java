package com.paldomoa.admin.domain;

import com.paldomoa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<com.paldomoa.member.domain.Member, Long> {

    Optional<Member> findByEmail(String email);

}
