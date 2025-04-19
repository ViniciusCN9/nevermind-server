package com.nevermind.server.domain.entity;

import com.nevermind.server.domain.entity.Default;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends Default {

    @Column(nullable = false, unique = true)
    private String name;
}
