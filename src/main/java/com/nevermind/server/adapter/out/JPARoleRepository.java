package com.nevermind.server.adapter.out;

import com.nevermind.server.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPARoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
