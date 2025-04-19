package com.nevermind.server.domain.port.out;

import com.nevermind.server.domain.entity.Account;

import java.util.Optional;

public interface AccountPort {

    Optional<Account> findById(Integer id);

    Optional<Account> findByUserId(Integer userId);

    Account saveAccount(Account account);

    void deleteById(Integer id);
}
