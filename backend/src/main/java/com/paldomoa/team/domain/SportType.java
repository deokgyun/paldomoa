package com.paldomoa.team.domain;

public enum SportType {

    SOCCER("축구"),
    FUTSAL("풋살"),
    BASEBALL("야구"),
    BASKETBALL("농구");

    private String name;

    public String getName() {
        return name;
    }

    SportType(String name) {
        this.name = name;
    }
}
