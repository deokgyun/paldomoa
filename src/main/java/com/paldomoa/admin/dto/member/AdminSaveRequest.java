package com.paldomoa.admin.dto.member;

import com.paldomoa.common.domain.eenum.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminSaveRequest {


    private String email;
    private String nickname;
    private String password;
    private String name;
    private String address;

    private RoleType role = RoleType.ROLE_USER;
    private String grade = "일반";
    private String status = "USE";

}
