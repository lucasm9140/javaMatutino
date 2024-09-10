create database creche;
USE creche;

CREATE TABLE cadastrocriancas (
    Id INT PRIMARY KEY auto_increment NOT NULL,
    Nome CHAR(45) NOT NULL,
    Cpfresponsaveis CHAR(11),
    Datanascimento DATE NOT NULL,
    Telefone VARCHAR(12) NOT NULL,
    Turma CHAR(4) NOT NULL,
    Endereco VARCHAR(30) NOT NULL,
    Horario ENUM("Integral", "Meio-Periodo") NOT NULL
);

CREATE TABLE cadastroprofessores (
    id INT PRIMARY KEY auto_increment NOT NULL,
    Nome VARCHAR(45) NOT NULL,
    Disciplina VARCHAR(20) NOT NULL,
    Endereco VARCHAR(30) NOT NULL,
    Turma CHAR(4) NOT NULL,
    fk_Turmas_id INT NOT NULL
);

CREATE TABLE Turmas (
    id INT PRIMARY KEY auto_increment NOT NULL,
    Nome_Professor VARCHAR(45) NOT NULL,
    Turma CHAR(4) NOT NULL,
    fk_cadastro_de_professores_id INT NOT NULL,
    fk_Cadastro_de_criancas_Id INT NOT NULL
);

 
