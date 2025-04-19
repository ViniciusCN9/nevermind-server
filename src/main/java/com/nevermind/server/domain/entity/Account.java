package com.nevermind.server.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "tb_account")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Account extends Default {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "public_key", nullable = false, columnDefinition = "TEXT")
    private String publicKey;

    @Column(name = "private_key_encrypted", nullable = false, columnDefinition = "TEXT")
    private String privateKeyEncrypted;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "last_seen")
    private OffsetDateTime lastSeen;

    public Account(User user, String publicKey, String privateKeyEncrypted, String name, String description) {
        this.user = user;
        this.publicKey = publicKey;
        this.privateKeyEncrypted = privateKeyEncrypted;
        this.name = name;
        this.description = description;
    }

    public void updateLastSeen() {
        this.lastSeen = OffsetDateTime.now();
    }
}
