CREATE TABLE usuario(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    campus VARCHAR(255),
    setor VARCHAR(255),
    cpf VARCHAR(255) UNIQUE,
    email_confirmed BOOLEAN DEFAULT false,
);
