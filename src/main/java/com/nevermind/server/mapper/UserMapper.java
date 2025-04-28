package com.nevermind.server.mapper;

import com.nevermind.server.domain.dto.SignupOutbound;
import com.nevermind.server.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    SignupOutbound toSignupOutbound(User user);
}
