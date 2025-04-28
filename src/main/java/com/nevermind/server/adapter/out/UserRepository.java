package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.User;
import com.nevermind.server.domain.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserPort {

    private final JPAUserRepository jpaUserRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }
}
