package com.paldomoa.admin.dto.member;

import com.paldomoa.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminSaveResponse {

    private Long id;

    private AdminSaveResponse(Long id) {
        this.id = id;
    }

    public static AdminSaveResponse from(Member member) {
        return new AdminSaveResponse(member.getId());
    }
}
