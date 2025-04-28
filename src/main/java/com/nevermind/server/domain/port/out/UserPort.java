package com.nevermind.server.domain.port.out;

import com.nevermind.server.domain.entity.User;

import java.util.Optional;

public interface UserPort {

    Optional<User> findByUsername(String username);

    User saveUser(User user);
}
