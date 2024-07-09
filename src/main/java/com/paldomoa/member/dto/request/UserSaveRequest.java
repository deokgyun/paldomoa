package com.paldomoa.member.dto.request;

public record UserSaveRequest(
    String email,
    String password,
    String nickname,
    String street,
    String city,
    String zipcode,
    String phone
) {


}
