CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS evento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    imagem_url VARCHAR(100) NOT NULL,
    evento_url VARCHAR(100) NOT NULL,
    data TIMESTAMP NOT NULL,
    remoto BOOLEAN NOT NULL
);
