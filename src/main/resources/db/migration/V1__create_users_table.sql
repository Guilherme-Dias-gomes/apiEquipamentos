CREATE TABLE Users(
    id BIGINT AUTO_INCREMENT primary key,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(30) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    setor VARCHAR(50) NOT NULL,
    role TEXT NOT NULL
);