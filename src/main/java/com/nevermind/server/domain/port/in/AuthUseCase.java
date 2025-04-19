package com.nevermind.server.domain.port.in;

import com.nevermind.server.domain.dto.auth.SignupInbound;
import com.nevermind.server.domain.dto.auth.SignupOutbound;
import com.nevermind.server.domain.dto.auth.LoginInbound;
import com.nevermind.server.domain.dto.auth.LoginOutbound;

public interface AuthUseCase {

    SignupOutbound signup(SignupInbound inbound);

    LoginOutbound login(LoginInbound inbound);
}
