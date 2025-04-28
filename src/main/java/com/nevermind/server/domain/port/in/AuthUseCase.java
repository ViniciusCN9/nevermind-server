package com.nevermind.server.domain.port.in;

import com.nevermind.server.domain.dto.SignupInbound;
import com.nevermind.server.domain.dto.SignupOutbound;
import com.nevermind.server.domain.dto.LoginInbound;
import com.nevermind.server.domain.dto.LoginOutbound;

public interface AuthUseCase {

    SignupOutbound signup(SignupInbound inbound);

    LoginOutbound login(LoginInbound inbound);
}
