package com.nevermind.server.adapter.in.controller;

import com.nevermind.server.domain.dto.LoginInbound;
import com.nevermind.server.domain.dto.LoginOutbound;
import com.nevermind.server.domain.dto.SignupInbound;
import com.nevermind.server.domain.dto.SignupOutbound;
import com.nevermind.server.domain.port.in.AuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public ResponseEntity<SignupOutbound> signup(@RequestBody @Valid SignupInbound inbound) {
        SignupOutbound outbound = authUseCase.signup(inbound);
        return ResponseEntity.ok(outbound);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutbound> login(@RequestBody @Valid LoginInbound inbound) {
        LoginOutbound outbound = authUseCase.login(inbound);
        return ResponseEntity.ok(outbound);
    }
}
