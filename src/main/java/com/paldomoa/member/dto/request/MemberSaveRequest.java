package com.paldomoa.member.dto.request;

public record MemberSaveRequest(
    String email,
    String password,
    String nickname,
    String street,
    String city,
    String zipcode
) {


}
