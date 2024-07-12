package com.paldomoa.auth.domain;

import com.paldomoa.common.domain.RoleType;
import lombok.Getter;

@Getter
public class Accessor {

    private final Long memberId;
    private final RoleType role;

    private Accessor(Long memberId, RoleType role) {
        this.memberId = memberId;
        this.role = role;
    }


    public static Accessor guest() {
        return new Accessor(0L, RoleType.ROLE_GUEST);
    }

    public static Accessor member(final Long memberId) {
        return new Accessor(memberId, RoleType.ROLE_MEMBER);
    }

    public static Accessor admin(final Long memberId) {
        return new Accessor(memberId, RoleType.ROLE_ADMIN);
    }


    public boolean isMember() {
        return RoleType.ROLE_MEMBER.equals(role);
    }

    public boolean isAdmin() {
        return RoleType.ROLE_ADMIN.equals(role);
    }

}
