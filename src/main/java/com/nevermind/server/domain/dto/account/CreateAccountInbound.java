package com.nevermind.server.domain.dto.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountInbound {

    @NotNull(message = "Public key cannot be null")
    private String publicKey;

    @NotNull(message = "Private key encrypted cannot be null")
    private String privateKeyEncrypted;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @Size(max = 2000, message = "Description must be less than 2000 characters")
    private String description;
}
