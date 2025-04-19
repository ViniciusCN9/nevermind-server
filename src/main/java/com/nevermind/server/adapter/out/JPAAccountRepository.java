package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAAccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUserId(Integer userId);
}
