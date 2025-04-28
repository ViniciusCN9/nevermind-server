package com.nevermind.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginOutbound {

    private String token;
    private Long expiresIn;
}
