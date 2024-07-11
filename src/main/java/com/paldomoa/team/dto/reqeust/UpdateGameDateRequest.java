package com.paldomoa.team.dto.reqeust;

import java.time.LocalDateTime;

public record UpdateGameDateRequest(
    LocalDateTime date,
    String description,
    LocationRequest locationRequest
) {

}
