package com.paldomoa.festival.domain;

import com.paldomoa.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FestivalInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_info_id")
    private Long id;

    private int price;
    private String organizers;
    private String address;
    private String tel;
    private String socialAddress;

}
