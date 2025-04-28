package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
