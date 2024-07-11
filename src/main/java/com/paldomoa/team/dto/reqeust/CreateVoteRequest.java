package com.paldomoa.team.dto.reqeust;

import com.paldomoa.member.domain.Member;
import com.paldomoa.team.domain.GameDate;
import com.paldomoa.team.domain.Vote;

public record CreateVoteRequest(
    Boolean isVote,
    Boolean isAttend
) {

    public Vote createVote(CreateVoteRequest request, Member member, GameDate gameDate) {
        return Vote.builder()
            .isVote(request.isVote())
            .teamAndMember(null)
            .gameDate(gameDate)
            .build();
    }
}
