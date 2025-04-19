package com.nevermind.server.adapter.in;

import com.nevermind.server.domain.dto.account.AccountOutbound;
import com.nevermind.server.domain.dto.account.CreateAccountInbound;
import com.nevermind.server.domain.port.in.AccountUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/account")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase accountUseCase;

    @PostMapping
    public ResponseEntity<AccountOutbound> createAccount(@RequestBody @Valid CreateAccountInbound inbound) {
        AccountOutbound outbound = accountUseCase.createAccount(inbound);
        return ResponseEntity.ok(outbound);
    }
}
