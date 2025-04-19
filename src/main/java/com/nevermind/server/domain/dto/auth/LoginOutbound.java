package com.nevermind.server.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class LoginOutbound {

    private String token;
    private Long expiresIn;
}
