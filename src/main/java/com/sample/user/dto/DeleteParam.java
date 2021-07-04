package com.sample.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteParam {
    @NotNull
    private String email;

    @NotNull
    private long eventId;
}
