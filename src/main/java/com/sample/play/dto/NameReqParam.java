package com.sample.play.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NameReqParam {
    @NotNull
    @JsonProperty("player")
    private String name;
}
