package com.nevermind.server.domain.dto;

import lombok.Data;

@Data
public class LoginInbound {

    private String username;
    private String password;
}
