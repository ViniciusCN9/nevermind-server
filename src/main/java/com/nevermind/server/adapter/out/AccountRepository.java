package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.Account;
import com.nevermind.server.domain.port.out.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements AccountPort {

    private final JPAAccountRepository jpaAccountRepository;

    @Override
    public Optional<Account> findById(Integer id) {
        return jpaAccountRepository.findById(id);
    }

    @Override
    public Optional<Account> findByUserId(Integer userId) {
        return jpaAccountRepository.findByUserId(userId);
    }

    @Override
    public Account saveAccount(Account account) {
        return jpaAccountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        jpaAccountRepository.deleteById(id);
    }
}
