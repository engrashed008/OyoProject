package com.sample.play.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateReqParam {
    @JsonProperty("player")
    private String name;

    @Min(1)
    private Integer score;

    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private String createdAt;
}
