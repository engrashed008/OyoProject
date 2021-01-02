package com.sample.play.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScoreListReqParam {
    @JsonProperty("player")
    private List<String> names;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String beforeTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String afterTime;


}
