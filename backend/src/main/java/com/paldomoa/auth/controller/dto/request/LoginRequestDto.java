package com.paldomoa.auth.controller.dto.request;

public record LoginRequestDto(
    String email,
    String password
) {

}
