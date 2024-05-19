package com.paldomoa.festival.domain;


import com.paldomoa.common.domain.BaseTimeEntity;
import com.paldomoa.common.domain.SidoAreas;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Festival extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_id")
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String category;

    @ManyToOne
    @JoinColumn(name = "sido_areas_id")
    private SidoAreas sidoAreas;

    @OneToOne
    @JoinColumn(name = "festival_info_id")
    private FestivalInfo festivalInfo;

}
