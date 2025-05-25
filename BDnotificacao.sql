drop database notifica;
create database notifica;
use notifica;

CREATE TABLE usuarios (
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    PRIMARY KEY (cpf)
);

CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    preco DOUBLE NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE notificacoes (
    id BIGINT AUTO_INCREMENT NOT NULL,
    usuario_cpf VARCHAR(14) NOT NULL,
    produto_id BIGINT NOT NULL,
    mensagem TEXT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'pendente',
    PRIMARY KEY (id),
    CONSTRAINT fk_notificacao_usuarios FOREIGN KEY (usuario_cpf) REFERENCES usuarios(cpf) ON DELETE CASCADE,
    CONSTRAINT fk_notificacao_produtos FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

select * from usuarios;
select * from produtos;