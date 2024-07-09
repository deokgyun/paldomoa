package com.paldomoa.member.dto.response;

import com.paldomoa.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveResponse {

    private Long id;

    private UserSaveResponse(Long id) {
        this.id = id;
    }

    public static UserSaveResponse from(Member member) {
        return new UserSaveResponse(member.getId());
    }
}
