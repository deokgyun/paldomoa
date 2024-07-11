package com.paldomoa.team.service;

import com.paldomoa.common.domain.Location;
import com.paldomoa.common.dto.request.SearchDto;
import com.paldomoa.common.exception.ApiException;
import com.paldomoa.common.exception.ExceptionData;
import com.paldomoa.member.domain.Member;
import com.paldomoa.member.domain.repository.MemberRepository;
import com.paldomoa.team.domain.GameDate;
import com.paldomoa.team.domain.Team;
import com.paldomoa.team.domain.Vote;
import com.paldomoa.team.domain.repository.GameDateRepository;
import com.paldomoa.team.domain.repository.TeamRepository;
import com.paldomoa.team.domain.repository.VoteRepository;
import com.paldomoa.team.dto.reqeust.CreateTeamRequest;
import com.paldomoa.team.dto.reqeust.CreateVoteRequest;
import com.paldomoa.team.dto.reqeust.GameDateLocationRequest;
import com.paldomoa.team.dto.reqeust.UpdateGameDateRequest;
import com.paldomoa.team.dto.reqeust.UpdateTeamRequest;
import com.paldomoa.team.dto.response.TeamsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final GameDateRepository gameDateRepository;
    private final VoteRepository voteRepository;

    // 팀 생성
    @Transactional(readOnly = false)
    public Long createTeam(CreateTeamRequest request, Long id) {

        Member createMember = memberRepository.findById(id)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_MEMBER_ID));

        Team team = request.createTeam(request, createMember);

        return teamRepository.save(team).getId();
    }

    // 팀 수정하기
    @Transactional(readOnly = false)
    public void updateTeam(Long teamId, UpdateTeamRequest request, Long id) {

//        Member createMember = memberRepository.findById(id)
//            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_MEMBER_ID));

        Team findTeam = teamRepository.findById(teamId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_TEAM));

        findTeam.updateTeam(request);
    }

    // 팀 삭제하기
    @Transactional(readOnly = false)
    public void deleteTeam(Long teamId) {

        Team findTeam = teamRepository.findById(teamId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_TEAM));

        teamRepository.delete(findTeam);
    }

    // 팀 리스트
    public Page<TeamsResponse> getAllTeam(SearchDto searchDto, Pageable pageable) {
        List<TeamsResponse> result = teamRepository.findAll()
            .stream()
            .map(team -> new TeamsResponse(team))
            .toList();

        int total = result.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), total);

        result = result.subList(start, end);

        return new PageImpl<>(result, pageable, total);
    }

    // 팀 상세 조회
    public TeamsResponse getTeam(Long id) {

        return new TeamsResponse(teamRepository.findById(id)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_TEAM)));
    }

    // 팀 운동일 올리기
    @Transactional(readOnly = false)
    public Long createGameDate(GameDateLocationRequest request, Long teamId, Long memberId) {
        Team findTeam = teamRepository.findById(teamId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_TEAM));

        Location findLocation = request.locationRequest().createLocation(request.locationRequest());
        GameDate gameDate = request.gameDateRequest()
            .createGameDate(request.gameDateRequest(), findTeam, findLocation);

        return gameDateRepository.save(gameDate).getId();
    }

    // 팀 운동일 투표 수정하기
    @Transactional(readOnly = false)
    public void updateGameDate(UpdateGameDateRequest request, Long gameDateId,
        Long memberId) {

        Location location = request.locationRequest().createLocation(request.locationRequest());

        gameDateRepository.findById(gameDateId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_GAME_DATE))
            .updateGameDate(request, location);
    }

    // TODO : team and member 개발하여 연관관계 추가해야함
    @Transactional(readOnly = false)
    public Long createVote(CreateVoteRequest request, Long gameDateId, Long memberId) {

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_MEMBER_ID));
        GameDate gameDate = gameDateRepository.findById(gameDateId)
            .orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_GAME_DATE));

        Vote vote = request.createVote(request, member, gameDate);
        return voteRepository.save(vote).getId();
    }

    // 팀 운동일 투표 하기

    // 팀 운동일 리스트

    // 팀 운동일 투표 상세 조회

    // ------------------

    // 매칭 모집 글 쓰기

    // 매칭 모집 글 수정하기

    // 매칭 모집 리스트

    // 매칭 모집 상세 조회

    // 매칭 신청하기

    // 매칭신청한 팀 보여주기

    // 매칭 수락하기
}
