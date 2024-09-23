CREATE DATABASE advocacia_sistem;

USE advocacia_sistem;

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    tipo ENUM('PF', 'PJ'),
    cpf VARCHAR(14) NULL,
    cnpj VARCHAR(18) NULL
);

CREATE TABLE Advogado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    honorarios DOUBLE,
    areaAtuacao VARCHAR(255)
);

CREATE TABLE Juiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE Tribunal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE Processo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clienteId INT,
    tribunalId INT,
    juizId INT,
    decisao VARCHAR(255),
    FOREIGN KEY (clienteId) REFERENCES Cliente(id),
    FOREIGN KEY (tribunalId) REFERENCES Tribunal(id),
    FOREIGN KEY (juizId) REFERENCES Juiz(id)
);
