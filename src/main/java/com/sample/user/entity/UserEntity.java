package com.sample.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserEntity {
    @JsonProperty("user_id")
    private String id;
    @JsonProperty("nickname")
    private String name;
    private String comments;
    private String deleteFlg;
}
