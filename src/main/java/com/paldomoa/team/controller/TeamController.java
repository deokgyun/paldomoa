package com.paldomoa.team.controller;

import com.paldomoa.common.dto.request.SearchDto;
import com.paldomoa.team.dto.reqeust.CreateTeamRequest;
import com.paldomoa.team.dto.reqeust.CreateVoteRequest;
import com.paldomoa.team.dto.reqeust.GameDateLocationRequest;
import com.paldomoa.team.dto.reqeust.UpdateGameDateRequest;
import com.paldomoa.team.dto.reqeust.UpdateTeamRequest;
import com.paldomoa.team.dto.response.TeamsResponse;
import com.paldomoa.team.service.TeamService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    // 팀 생성
    @PostMapping
    public ResponseEntity<Long> createTeam(//@Member Long id,
        @RequestBody CreateTeamRequest request) {

//        teamService.createTeam(request, id);

        return ResponseEntity.ok().body(teamService.createTeam(request, 1L));
    }

    // 팀 수정
    @PatchMapping("/{teamId}")
    public ResponseEntity<Void> updateTeam(//@Member Long id,
        @RequestBody UpdateTeamRequest request, @PathVariable Long teamId) {

        teamService.updateTeam(teamId, request, 1L);

        return ResponseEntity.ok().build();
    }

    // 팀 전체 조회하기
    @GetMapping
    public ResponseEntity<Page<TeamsResponse>> deleteTeam(//@Member Long id,
        SearchDto searchDto,
        @PageableDefault(size = 20, direction = Sort.Direction.DESC) @Parameter(hidden = true) Pageable pageable) {

        return ResponseEntity.ok().body(teamService.getAllTeam(searchDto, pageable));
    }

    // 팀 상세 조회하기
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamsResponse> getTeam(//@Member Long id,
        @PathVariable Long teamId) {

        return ResponseEntity.ok().body(teamService.getTeam(teamId));
    }


    // 운동일 올리기 - 올리면서 투표 진행
    @PostMapping("/{teamId}/game")
    public ResponseEntity<Long> createGameDate(//@Member Long id,
        @RequestBody GameDateLocationRequest request, @PathVariable Long teamId) {

//        teamService.createTeam(request, id);

        return ResponseEntity.ok().body(teamService.createGameDate(request, teamId, 1L));
    }

    // 팀 운동일 수정하기
    @PatchMapping("/{teamId}/game/{gameDateId}")
    public ResponseEntity<Void> updateGameDate(//@Member Long id,
        @RequestBody UpdateGameDateRequest request,
        @PathVariable Long teamId,
        @PathVariable Long gameDateId) {

        teamService.updateGameDate(request, gameDateId, 1L);

        return ResponseEntity.ok().build();
    }

    // 팀 운동일 투표 하기
    @PostMapping("/{teamId}/game/{gameDateId}/vote")
    public ResponseEntity<Long> createVote(//@Member Long id,
        @RequestBody CreateVoteRequest request,
        @PathVariable Long teamId,
        @PathVariable Long gameDateId) {

        return ResponseEntity.ok().body(teamService.createVote(request, gameDateId, 1L));
    }

    // 실제 운동 여부 체크하기

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
