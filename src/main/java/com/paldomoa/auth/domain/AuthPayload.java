package com.paldomoa.auth.domain;

import com.paldomoa.common.domain.eenum.RoleType;
import java.rmi.UnexpectedException;

public class AuthPayload {

    private Long memberId;
    private RoleType roleType;

    public AuthPayload(Long memberId, RoleType roleType) throws UnexpectedException {
        validate(roleType);
        this.memberId = memberId;
        this.roleType = roleType;
    }

    private void validate(RoleType roleType) throws UnexpectedException {
        if (roleType == null) {
            throw new UnexpectedException("ROLE은 null이 될 수 없습니다.");
        }
    }

    public Long getMemberId() {
        return memberId;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}
