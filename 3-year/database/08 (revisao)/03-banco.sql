USE banco;

CREATE TABLE contacorrente (
  nconta  INT							NOT NULL PRIMARY KEY,
  cliente VARCHAR(100)  	NOT NULL,
  saldo   DECIMAL(12, 2) 	NOT NULL,
);

CREATE TABLE movimentocc (
  id        INT             NOT NULL IDENTITY(1, 1)  PRIMARY KEY,
  nconta    INT       		  NOT NULL,
  dtmov     DATETIME        NOT NULL,
  valor     DECIMAL(12, 2)  NOT NULL,
  operacao  CHAR            NOT NULL, -- D or S --

  CONSTRAINT fk_cc_movimento FOREIGN KEY (nconta) REFERENCES contacorrente (nconta),
);

CREATE TABLE operacao (
  id          INT           	NOT NULL IDENTITY(1, 1) PRIMARY KEY,
  operacao    CHAR          	NOT NULL,
  valor       DECIMAL(12, 2) 	NOT NULL,
  dtoperacao  DATETIME      	NOT NULL,
);

CREATE TABLE auditoria (
  id          INT              NOT NULL IDENTITY(1, 1) PRIMARY KEY,
  funcionario VARCHAR(100)     NOT NULL,
  operacao    CHAR             NOT NULL, -- D or S --
  valor       DECIMAL(12, 2)   NOT NULL,
  dtoperacao  DATETIME         NOT NULL,
);

INSERT INTO contacorrente (nconta, cliente, saldo) 
VALUES 
  (139911,  'Celeblorn', 763),
  (205467,  'Goave',     15000),
  (1067230, 'Nosbo',     23),
  (105679,  'Elion',     175300);

-- Queries
CREATE VIEW view_mov_deposito AS
SELECT * FROM contacorrente;

CREATE TRIGGER tr_movimentacao_deposito
ON movimentocc
AFTER INSERT
AS 
  BEGIN
    DECLARE 
      @nconta INT,
      @dtmov DATETIME,
      @valor DECIMAL(12, 2),
      @operacao CHAR;

    SELECT 
      nconta = @nconta, 
      dtmov = @dtmov, 
      valor = @valor, 
      operacao = @operacao 
    FROM selected;

    IF (@operacao != 'D')
      RETURN;

    UPDATE contacorrente
    SET saldo += @valor
    WHERE nconta = @nconta;

    INSERT INTO auditoria (funcionario, operacao, valor, dtoperacao) 
    VALUES ('Matheus', @operacao, @valor, @dtmov);    

    SELECT * FROM view_mov_deposito WHERE nconta = @nconta;
  END;