package com.sample.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequestParam {
    @JsonProperty("nickname")
    private String name;

    @NotNull
    @JsonProperty("user_id")
    private String id;

    @NotNull
    private String password;

    @JsonProperty("comment")
    private String comments;

}
