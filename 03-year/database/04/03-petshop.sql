CREATE DATABASE clinica;

USE clinica;

-- create tables -- 
CREATE TABLE veterinarios (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(20) NOT NULL,
  tel   VARCHAR(12) NOT NULL
);

CREATE TABLE funcionarios (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(20) NOT NULL,
  cargo VARCHAR(20) NOT NULL
);

CREATE TABLE proprietarios (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(40) NOT NULL,
  tel   VARCHAR(12) NOT NULL,
  email VARCHAR(40) NOT NULL,
  idpet INT         NOT NULL
);

CREATE TABLE pets (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(20) NOT NULL,
  raca  VARCHAR(20) NOT NULL,
  idade INT         NOT NULL
);

CREATE TABLE convenios (
	id        INT           NOT NULL UNIQUE,
  nome      VARCHAR(20)   NOT NULL,
  DESConto  DECIMAL(7,2)  NOT NULL
);

CREATE TABLE medicamentos (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(20) NOT NULL,
  valor DECIMAL(7,2) NOT NULL
);

CREATE TABLE exames (
	id    INT         NOT NULL UNIQUE,
  nome  VARCHAR(20) NOT NULL,
  valor DECIMAL(7,2) NOT NULL
);

CREATE TABLE consultas (
  id              INT           NOT NULL UNIQUE,
	dataconsulta    DATETIME      NOT NULL,
  idveterinario   INT           NOT NULL,
  idfuncionario   INT           NOT NULL,
  idproprietario  INT           NOT NULL,
  idpet           INT           NOT NULL,
  idconvenio      INT           NOT NULL,
  idmedicamento   INT           NOT NULL,
  idexame         INT           NOT NULL,
  valor           DECIMAL(7,2)  NOT NULL
);

CREATE TABLE auditoria (
  id              INT           NOT NULL UNIQUE,
	dataconsulta    DATETIME      NOT NULL,
  idveterinario   INT           NOT NULL,
  idfuncionario   INT           NOT NULL,
  idproprietario  INT           NOT NULL,
  idpet           INT           NOT NULL,
  idconvenio      INT           NOT NULL,
  idmedicamento   INT           NOT NULL,
  idexame         INT           NOT NULL,
  valor           DECIMAL(7,2)  NOT NULL
);

-- trigger --
CREATE TRIGGER tr_populate_auditoria
ON consultas
AFTER INSERT
AS
BEGIN
  DECLARE
  @id             INT,
  @dataconsulta   DATETIME,
  @idveterinario  INT,
  @idfuncionario  INT,
  @idproprietario INT,
  @idpet          INT,
  @idconvenio     INT,
  @idmedicamento  INT,
  @idexame        INT,
  @valor          DECIMAL(7, 2);

  SELECT 
    @id = id,
    @dataconsulta = dataconsulta,
    @idveterinario = idveterinario,
    @idfuncionario = idfuncionario,
    @idproprietario = idproprietario,
    @idpet = idpet,
    @idconvenio = idconvenio,
    @idmedicamento = idmedicamento,
    @idexame = idexame,
    @valor = valor
  FROM inserted;

  INSERT INTO auditoria VALUES (
    @id,
    @dataconsulta,
    @idveterinario,
    @idfuncionario,
    @idproprietario,
    @idpet,
    @idconvenio,
    @idmedicamento,
    @idexame,
    @valor
  );
END;

-- populate tables (seeders) --
INSERT INTO veterinarios VALUES (1, 'Roberta', '234234234234');
INSERT INTO veterinarios VALUES (2, 'Gabriela', '232526436463');
INSERT INTO veterinarios VALUES (3, 'Arthur', '242342412311');
INSERT INTO veterinarios VALUES (4, 'Maicon', '423423324255');
INSERT INTO veterinarios VALUES (5, 'Valéria', '746435453');

INSERT INTO funcionarios VALUES (1, 'Renata', 'Secretária');
INSERT INTO funcionarios VALUES (2, 'Eduardo', 'Enfermeiro');
INSERT INTO funcionarios VALUES (3, 'Alita', 'Secretária');
INSERT INTO funcionarios VALUES (4, 'Bruno', 'Atendimento');
INSERT INTO funcionarios VALUES (5, 'Bruna', 'Atendimento');

INSERT INTO pets VALUES (1, 'Thor', 'Border Collie', 1);
INSERT INTO pets VALUES (2, 'Floquinhos', 'Dálmata', 8);
INSERT INTO pets VALUES (3, 'Bidu', 'Schnauzer', 18);
INSERT INTO pets VALUES (4, 'Lilica', 'Akita', 2);
INSERT INTO pets VALUES (5, 'Totó', 'Caramelo', 5);

INSERT INTO proprietarios VALUES (1, '324242342', 'Osorio', 'osorio@email.com', 1);
INSERT INTO proprietarios VALUES (2, '53453534', 'Roberta', 'roberta@email.com', 2);
INSERT INTO proprietarios VALUES (3, '3423252', 'Andrey', 'andreygames@email.com', 3);
INSERT INTO proprietarios VALUES (4, '131423524', 'Joziane', 'jojobarrafunda@email.com', 4);
INSERT INTO proprietarios VALUES (5, '34525446', 'Clayton', 'clayuotondaleste@email.com', 5);

INSERT INTO convenios VALUES (1, 'QualiSaude', 0.15);
INSERT INTO convenios VALUES (2, 'Saude Sem Limites', 0.05);
INSERT INTO convenios VALUES (3, 'VivePraValer', 0.3);
INSERT INTO convenios VALUES (4, 'UniPlan', 0.75);
INSERT INTO convenios VALUES (5, 'Santa Saude', 0.9);

INSERT INTO medicamentos VALUES (1, 'Acetilcisteína', 120);
INSERT INTO medicamentos VALUES (2, 'Cetorolaco', 18);
INSERT INTO medicamentos VALUES (3, 'Gatifloxacino', 10);
INSERT INTO medicamentos VALUES (4, 'Ganciclovir', 60);
INSERT INTO medicamentos VALUES (5, 'PravAStatina', 32.5);

INSERT INTO exames VALUES (1, 'Sangue', 120);
INSERT INTO exames VALUES (2, 'Ressonância Torácica', 750);
INSERT INTO exames VALUES (3, 'Urina', 150);
INSERT INTO exames VALUES (4, 'UltrASsonografia', 500);
INSERT INTO exames VALUES (5, 'Raio X', 300);

INSERT INTO consultas VALUES (1, GETDATE(), 1, 1, 1, 1, 1, 1, 1, 500);
INSERT INTO consultas VALUES (2, GETDATE(), 1, 2, 1, 1, 1, 1, 3, 400);
INSERT INTO consultas VALUES (3, GETDATE(), 1, 3, 2, 2, 3, 2, 5, 487);
INSERT INTO consultas VALUES (4, GETDATE(), 2, 4, 3, 3, 1, 3, 2, 550);
INSERT INTO consultas VALUES (5, GETDATE(), 5, 5, 1, 1, 1, 4, 1, 870);
INSERT INTO consultas VALUES (6, GETDATE(), 4, 5, 4, 4, 2, 5, 4, 543);
INSERT INTO consultas VALUES (7, GETDATE(), 3, 4, 3, 3, 1, 1, 2, 800);
INSERT INTO consultas VALUES (8, GETDATE(), 2, 2, 2, 2, 3, 4, 3, 642);
INSERT INTO consultas VALUES (9, GETDATE(), 1, 3, 3, 3, 1, 3, 5, 699);
INSERT INTO consultas VALUES (10, GETDATE(), 4, 1, 2, 2, 3, 2, 1, 520);
INSERT INTO consultas VALUES (11, GETDATE(), 5, 4, 4, 4, 2, 1, 2, 538);


-- queries -- 
SELECT * FROM consultas;
SELECT * FROM auditoria;
