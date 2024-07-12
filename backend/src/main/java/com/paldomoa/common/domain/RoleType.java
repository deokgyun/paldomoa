package com.paldomoa.common.domain;

import com.paldomoa.auth.annotation.Admin;
import com.paldomoa.auth.annotation.Member;
import java.lang.annotation.Annotation;
import java.rmi.UnexpectedException;

public enum RoleType {

    ROLE_MEMBER(Member.class),
    ROLE_ADMIN(Admin.class),

    ROLE_GUEST(null);

    private final Class<? extends Annotation> annotation;

    RoleType(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public static RoleType from(String role) throws UnexpectedException {
        try {
            return valueOf(role);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new UnexpectedException("해당하는 Role이 없습니다.");
        }
    }

}
