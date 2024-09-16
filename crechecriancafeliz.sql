CREATE DATABASE IF NOT EXISTS crechecriancafeliz;
USE crechecriancafeliz;

CREATE TABLE turma (
    id_turma int PRIMARY KEY auto_increment NOT NULL,
    idadeMin int NOT NULL,
    idadeMax int NOT NULL,
    nome VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE crianca (
    id_crianca int PRIMARY KEY auto_increment NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    datanascimento DATE NOT NULL,
    endereco VARCHAR(30) NOT NULL,
    telefone VARCHAR(12) NOT NULL,
    id_turma int NOT NULL,
    FOREIGN KEY (id_turma) REFERENCES turma(id_turma)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE professor (
    id_professor int PRIMARY KEY auto_increment NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    datanascimento DATE NOT NULL,
    endereco VARCHAR(30) NOT NULL,
    telefone VARCHAR(12) NOT NULL,
    disciplina VARCHAR(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE creche (
    id_creche int PRIMARY KEY auto_increment NOT NULL,
    id_turma int NOT NULL,
    id_crianca int NOT NULL,
    id_professor int NOT NULL,
    FOREIGN KEY (id_turma) REFERENCES turma(id_turma),
    FOREIGN KEY (id_crianca) REFERENCES crianca(id_crianca),
    FOREIGN KEY (id_professor) REFERENCES professor(id_professor)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
