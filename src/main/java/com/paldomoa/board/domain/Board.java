package com.paldomoa.board.domain;

import com.paldomoa.common.domain.BaseTimeEntity;
import com.paldomoa.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
