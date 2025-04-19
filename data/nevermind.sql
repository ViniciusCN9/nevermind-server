CREATE DATABASE IF NOT EXISTS nevermind;

CREATE TABLE IF NOT EXISTS tb_user (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    email VARCHAR(600) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS tb_role (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS tb_user_role (
    user_id INTEGER REFERENCES tb_user(id),
    role_id INTEGER REFERENCES tb_role(id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO tb_role (name, created_at) VALUES ( 'ROLE_USER', NOW() );

CREATE TABLE IF NOT EXISTS tb_account (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    public_key TEXT NOT NULL,
    private_key_encrypted TEXT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    last_seen TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE
);