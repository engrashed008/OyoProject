package com.sample.play.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoreEntity {
    private Long scoreId;
    private String name;
    private Integer score;
    private String createdAt;
}
