package com.paldomoa.member.domain;

import com.paldomoa.common.domain.Address;
import com.paldomoa.common.domain.BaseTimeEntity;
import com.paldomoa.common.domain.RoleType;
import com.paldomoa.member.dto.request.UserSaveRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 68)
    private String password;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 13)
    private String phone;

    @Embedded
    private Address address;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    public Member(UserSaveRequest request, PasswordEncoder passwordEncoder) {
        this.email = request.email();
        this.password = passwordEncoder.encode(request.password());
        this.nickname = request.nickname();
        this.phone = request.phone();
        this.address = new Address(request.city(), request.street(), request.zipcode());
        this.roleType = RoleType.ROLE_USER;
    }
}
