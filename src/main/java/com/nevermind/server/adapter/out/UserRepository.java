package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.Role;
import com.nevermind.server.domain.entity.User;
import com.nevermind.server.domain.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserPort {

    private final JPARoleRepository jpaRoleRepository;
    private final JPAUserRepository jpaUserRepository;

    @Override
    public Optional<User> findById(Integer id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<Role> findRoleByName(String role) {
        return jpaRoleRepository.findByName(role);
    }

    @Override
    public User saveUser(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        jpaUserRepository.deleteById(id);
    }
}
