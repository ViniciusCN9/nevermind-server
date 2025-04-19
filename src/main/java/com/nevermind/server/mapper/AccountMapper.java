package com.nevermind.server.mapper;

import com.nevermind.server.domain.dto.account.AccountOutbound;
import com.nevermind.server.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountOutbound toAccountOutbound(Account account);
}
