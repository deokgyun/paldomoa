package com.paldomoa.team.domain.repository;

import com.paldomoa.team.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
