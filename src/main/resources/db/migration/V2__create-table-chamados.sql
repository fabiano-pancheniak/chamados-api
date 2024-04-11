CREATE TABLE chamado(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status CHAR NOT NULL,
    atendente_id VARCHAR(255),
    usuario_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chamado_atendente_id_fkey FOREIGN KEY(atendente_id) REFERENCES usuario(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chamado_usuario_id_fkey FOREIGN KEY(usuario_id) REFERENCES usuario(id) ON DELETE RESTRICT ON UPDATE CASCADE
);
