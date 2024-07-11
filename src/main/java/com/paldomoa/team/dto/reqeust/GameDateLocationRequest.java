package com.paldomoa.team.dto.reqeust;

public record GameDateLocationRequest(
    CreateGameDateRequest gameDateRequest,
    LocationRequest locationRequest
) {

}
