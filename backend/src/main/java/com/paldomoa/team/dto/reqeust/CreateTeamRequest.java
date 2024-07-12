package com.paldomoa.team.dto.reqeust;

import com.paldomoa.member.domain.Member;
import com.paldomoa.team.domain.SportType;
import com.paldomoa.team.domain.Team;

public record CreateTeamRequest(
    String name,
    String description,
    SportType sportType

) {

    public Team createTeam(CreateTeamRequest request, Member createMember) {
        return Team.builder()
            .name(request.name())
            .description(request.description())
            .sportType(request.sportType())
            .member(createMember)
            .build();
    }

}
