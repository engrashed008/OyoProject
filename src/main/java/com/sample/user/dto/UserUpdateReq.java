package com.sample.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserUpdateReq {
    @JsonProperty("nickname")
    private String name;
    @JsonProperty("comment")
    private String comments;
}
