package com.nevermind.server.domain.service;

import com.nevermind.server.config.exception.BadRequestCustomException;
import com.nevermind.server.domain.dto.account.CreateAccountInbound;
import com.nevermind.server.domain.dto.account.AccountOutbound;
import com.nevermind.server.domain.entity.Account;
import com.nevermind.server.domain.entity.User;
import com.nevermind.server.domain.port.in.AccountUseCase;
import com.nevermind.server.domain.port.out.AccountPort;
import com.nevermind.server.domain.port.out.UserPort;
import com.nevermind.server.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {

    private final AccountPort accountPort;
    private final AccountMapper accountMapper;
    private final UserPort userPort;

    @Override
    @Transactional
    public AccountOutbound createAccount(CreateAccountInbound inbound) {
        User user = userPort.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        verifyUserAlreadyHasAccount(user.getId());

        Account account = new Account(
                user,
                inbound.getPublicKey(),
                inbound.getPrivateKeyEncrypted(),
                inbound.getName(),
                inbound.getDescription());
        Account createdAccount = accountPort.saveAccount(account);

        return accountMapper.toAccountOutbound(createdAccount);
    }

    private void verifyUserAlreadyHasAccount(Integer userId) {
        Optional<Account> accountWithSameUserId = accountPort.findByUserId(userId);
        if (accountWithSameUserId.isPresent()) {
            throw new BadRequestCustomException("User already has an account");
        }
    }
}
