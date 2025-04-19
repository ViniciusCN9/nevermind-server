package com.nevermind.server.domain.dto.auth;

import lombok.Data;

@Data
public class LoginInbound {

    private String username;
    private String password;
}
