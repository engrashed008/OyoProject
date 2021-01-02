package com.sample.play.resonse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.PrimitiveIterator;

@Data
public class ScoreReponseDto {
    private Long scoreId;
    private String name;
    private Integer score;
}
