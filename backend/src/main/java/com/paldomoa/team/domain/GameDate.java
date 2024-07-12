package com.paldomoa.team.domain;

import com.paldomoa.common.domain.BaseTimeEntity;
import com.paldomoa.common.domain.Location;
import com.paldomoa.team.dto.reqeust.UpdateGameDateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class GameDate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime gameDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void updateGameDate(UpdateGameDateRequest request, Location location) {
        this.description = request.description();
        this.gameDate = request.date();
        this.location = location;
    }
}
