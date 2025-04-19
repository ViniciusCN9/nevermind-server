package com.nevermind.server.domain.dto.auth;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class SignupOutbound {

    private Integer id;
    private String username;
    private String email;
    private Set<String> roles;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
