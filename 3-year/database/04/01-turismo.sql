CREATE DATABASE turismo;

USE turismo;

-- criação das tabelas
CREATE TABLE agente1 (
  id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  idcliente   INT             NOT NULL,
  destino     VARCHAR(255)    NOT NULL,
  dataviagem  DATETIME        NOT NULL,
  valorpacote DECIMAL(8, 2)   NOT NULL
);

CREATE TABLE agente2 (
	id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  idcliente   INT             NOT NULL,
  destino     VARCHAR(255)    NOT NULL,
  dataviagem  DATETIME        NOT NULL,
  valorpacote DECIMAL(8, 2)   NOT NULL
);

CREATE TABLE agente3 (
  id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  idcliente   INT             NOT NULL,
  destino     VARCHAR(255)    NOT NULL,
  dataviagem  DATETIME        NOT NULL,
  valorpacote DECIMAL(8, 2)   NOT NULL,
);

CREATE TABLE agente4 (
  id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  idcliente   INT             NOT NULL,
  destino     VARCHAR(255)    NOT NULL,
  dataviagem  DATETIME        NOT NULL,
  valorpacote DECIMAL(8, 2)   NOT NULL
);

CREATE TABLE viagens (
  idref       INT             NOT NULL,
  idagente    INT             NOT NULL,
  idcliente   INT             NOT NULL,
  destino     VARCHAR(255)    NOT NULL,
  dataviagem  DATETIME        NOT NULL,
  valorpacote DECIMAL(8, 2)   NOT NULL,

  PRIMARY KEY (idref, idagente, idcliente)
);

-- triggers
CREATE TRIGGER tr_unifica_viagens_agente_1
ON agente1
AFTER INSERT
AS 
BEGIN
    DECLARE 
    @idagente INT = 1,
    @idref INT,
    @idcliente INT,
    @destino VARCHAR(255),
    @dataviagem DATETIME,
    @valorpacote DECIMAL(8, 2);

    SELECT
      @idref = id,
      @idcliente = idcliente,
      @destino = destino,
      @dataviagem = dataviagem,
      @valorpacote = valorpacote
    FROM inserted;

    INSERT INTO viagens (
      idref, 
      idagente, 
      idcliente, 
      destino, 
      dataviagem, 
      valorpacote
    ) VALUES (
      @idref, 
      @idagente, 
      @idcliente, 
      @destino, 
      @dataviagem, 
      @valorpacote
    );
END;

CREATE TRIGGER tr_unifica_viagens_agente_2
ON agente2
AFTER INSERT
AS 
BEGIN
    DECLARE 
    @idagente INT = 2,
    @idref INT,
    @idcliente INT,
    @destino VARCHAR(255),
    @dataviagem DATETIME,
    @valorpacote DECIMAL(8, 2);

    SELECT
      @idref = id,
      @idcliente = idcliente,
      @destino = destino,
      @dataviagem = dataviagem,
      @valorpacote = valorpacote
    FROM inserted;

    INSERT INTO viagens (
      idref, 
      idagente, 
      idcliente, 
      destino, 
      dataviagem, 
      valorpacote
    ) VALUES (
      @idref, 
      @idagente, 
      @idcliente, 
      @destino, 
      @dataviagem, 
      @valorpacote
    );
END;

CREATE TRIGGER tr_unifica_viagens_agente_3
ON agente3
AFTER INSERT
AS 
BEGIN
    DECLARE 
    @idagente INT = 3,
    @idref INT,
    @idcliente INT,
    @destino VARCHAR(255),
    @dataviagem DATETIME,
    @valorpacote DECIMAL(8, 2);

    SELECT
      @idref = id,
      @idcliente = idcliente,
      @destino = destino,
      @dataviagem = dataviagem,
      @valorpacote = valorpacote
    FROM inserted;

    INSERT INTO viagens (
      idref, 
      idagente, 
      idcliente, 
      destino, 
      dataviagem, 
      valorpacote
    ) VALUES (
      @idref, 
      @idagente, 
      @idcliente, 
      @destino, 
      @dataviagem, 
      @valorpacote
    );
END;

CREATE TRIGGER tr_unifica_viagens_agente_4
ON agente4
AFTER INSERT
AS 
BEGIN
    DECLARE 
    @idagente INT = 4,
    @idref INT,
    @idcliente INT,
    @destino VARCHAR(255),
    @dataviagem DATETIME,
    @valorpacote DECIMAL(8, 2);

    SELECT
      @idref = id,
      @idcliente = idcliente,
      @destino = destino,
      @dataviagem = dataviagem,
      @valorpacote = valorpacote
    FROM inserted;

    INSERT INTO viagens (
      idref, 
      idagente, 
      idcliente, 
      destino, 
      dataviagem, 
      valorpacote
    ) VALUES (
      @idref, 
      @idagente, 
      @idcliente, 
      @destino, 
      @dataviagem, 
      @valorpacote
    );
END;

-- seeds
INSERT agente1 (idcliente, destino, dataviagem, valorpacote) VALUES (10, 'Porto Alegre', '2024-02-01 12:00:00', 1200);
INSERT agente1 (idcliente, destino, dataviagem, valorpacote) VALUES (20, 'Curitiba', '2024-02-02 12:00:00', 700);
INSERT agente1 (idcliente, destino, dataviagem, valorpacote) VALUES (30, 'Amazonas', '2024-02-03 12:00:00', 10000);
INSERT agente2 (idcliente, destino, dataviagem, valorpacote) VALUES (2, 'Ouro Preto', '2024-02-01 12:00:00', 1000);
INSERT agente2 (idcliente, destino, dataviagem, valorpacote) VALUES (3, 'São Paulo', '2024-02-01 12:00:00', 540);
INSERT agente3 (idcliente, destino, dataviagem, valorpacote) VALUES (4, 'São Paulo', '2024-03-01 12:00:00', 540);
INSERT agente4 (idcliente, destino, dataviagem, valorpacote) VALUES (4, 'São Paulo', '2024-03-01 12:00:00', 540);

SELECT * FROM agente1;
SELECT * FROM agente2;
SELECT * FROM agente3;
SELECT * FROM agente4;
SELECT * FROM viagens;
