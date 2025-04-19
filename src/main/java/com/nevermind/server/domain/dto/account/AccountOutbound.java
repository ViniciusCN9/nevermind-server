package com.nevermind.server.domain.dto.account;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AccountOutbound {

    private Integer id;
    private String name;
    private String description;
    private OffsetDateTime lastSeen;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
