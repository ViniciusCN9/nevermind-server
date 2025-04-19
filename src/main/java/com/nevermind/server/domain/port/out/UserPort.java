package com.nevermind.server.domain.port.out;

import com.nevermind.server.domain.entity.user.Role;
import com.nevermind.server.domain.entity.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {

    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<Role> findRoleByName(String role);

    User saveUser(User user);

    void deleteById(Integer id);
}
