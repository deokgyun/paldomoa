package com.paldomoa.team.dto.response;

import com.paldomoa.team.domain.Team;

public record TeamsResponse(
    Long teamId,
    String name,
    String description,
    String sportType
) {

    public TeamsResponse(Team team) {
        this(
            team.getId(),
            team.getName(),
            team.getDescription(),
            team.getSportType().name()
        );
    }
}
