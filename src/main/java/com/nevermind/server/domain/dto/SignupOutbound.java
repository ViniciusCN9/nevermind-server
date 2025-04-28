package com.nevermind.server.domain.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class SignupOutbound {

    private Integer id;
    private String username;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
