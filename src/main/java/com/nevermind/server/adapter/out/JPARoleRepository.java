package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JPARoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
