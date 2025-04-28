package com.nevermind.server.domain.service;

import com.nevermind.server.config.exception.BadRequestCustomException;
import com.nevermind.server.config.security.JwtService;
import com.nevermind.server.domain.dto.SignupInbound;
import com.nevermind.server.domain.dto.SignupOutbound;
import com.nevermind.server.domain.dto.LoginInbound;
import com.nevermind.server.domain.dto.LoginOutbound;
import com.nevermind.server.domain.entity.User;
import com.nevermind.server.domain.port.in.AuthUseCase;
import com.nevermind.server.domain.port.out.UserPort;
import com.nevermind.server.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserPort userPort;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignupOutbound signup(SignupInbound inbound) {
        verifyUserAlreadyExists(inbound.getUsername());

        User user = new User(
                inbound.getUsername(),
                passwordEncoder.encode(inbound.getPassword()));
        User createdUser = userPort.saveUser(user);

        return userMapper.toSignupOutbound(createdUser);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginOutbound login(LoginInbound inbound) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inbound.getUsername(),
                        inbound.getPassword()
                )
        );

        User user = userPort.findByUsername(inbound.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return new LoginOutbound(jwtService.generateToken(user), jwtService.getExpirationTime());
    }

    private void verifyUserAlreadyExists(String username) {
        Optional<User> userWithSameUsername = userPort.findByUsername(username);
        if (userWithSameUsername.isPresent()) {
            throw new BadRequestCustomException("User already exists");
        }
    }
}
