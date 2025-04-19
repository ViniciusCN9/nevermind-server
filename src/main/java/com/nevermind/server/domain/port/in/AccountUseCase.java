package com.nevermind.server.domain.port.in;

import com.nevermind.server.domain.dto.account.CreateAccountInbound;
import com.nevermind.server.domain.dto.account.AccountOutbound;

public interface AccountUseCase {

    AccountOutbound createAccount(CreateAccountInbound inbound);
}
