package com.sample.play.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteParam {
    @JsonProperty("id")
    private Long scoreId;
}
