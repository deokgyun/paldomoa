package com.paldomoa.team.dto.reqeust;

import com.paldomoa.common.domain.Location;
import com.paldomoa.team.domain.GameDate;
import com.paldomoa.team.domain.Team;
import java.time.LocalDateTime;

public record CreateGameDateRequest(
    LocalDateTime date,
    String description
) {


    public GameDate createGameDate(CreateGameDateRequest request, Team findTeam,
        Location findLocation) {

        return GameDate.builder()
            .gameDate(request.date())
            .description(request.description())
            .team(findTeam)
            .location(findLocation)
            .build();

    }
}
