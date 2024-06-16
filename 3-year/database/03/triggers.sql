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
  
SELECT * FROM contacorrente;

-- triggers --
-- movimentação financeira de depósito
CREATE TRIGGER tr_mov_deposito
ON movimentocc
AFTER INSERT, UPDATE
AS
BEGIN
  IF (SELECT COUNT(*) FROM inserted) = 1
    BEGIN
    	DECLARE
      	@nconta 	INT,
        @dtmov 		DATETIME,
        @valor 		DECIMAL(12, 2),
        @operacao CHAR;
        
      SELECT 
      	@nconta = nconta, 
      	@dtmov = dtmov,
        @valor = valor,
        @operacao = operacao
      FROM inserted;
      
      IF (@operacao != 'D' AND @operacao != 'S')
        BEGIN
          RAISERROR('operação inválida', 16, 1);
          ROLLBACK TRANSACTION;
          RETURN;
        END;
       
      IF @operacao != 'D'
      	RETURN;

      UPDATE contacorrente
      SET saldo = saldo + @valor
      WHERE nconta = @nconta;


      INSERT INTO operacao (operacao, valor, dtoperacao) 
      VALUES (@operacao, @valor, @dtmov);


      INSERT INTO auditoria (funcionario, operacao, valor, dtoperacao) 
      VALUES ('Matheus', @operacao, @valor, @dtmov);
    END;
END;

SELECT * FROM contacorrente WHERE nconta = 205467;
INSERT INTO movimentocc (nconta, dtmov, valor, operacao) VALUES (205467, '2022-10-15 15:30:00', 1000, 'D');
INSERT INTO movimentocc (nconta, dtmov, valor, operacao) VALUES (205467, '2022-10-15 15:35:00', 1000, 'K'); -- erro!
SELECT * FROM contacorrente WHERE nconta = 205467;

-- movimentação financeira de saque
CREATE TRIGGER tr_mov_saque
ON movimentocc
AFTER INSERT, UPDATE
AS
BEGIN
  IF (SELECT COUNT(*) FROM inserted) = 1
    BEGIN
    	DECLARE
      	@nconta 	    INT,
        @dtmov 		    DATETIME,
        @valor 		    DECIMAL(12, 2),
        @operacao     CHAR,
        @saldo_atual  DECIMAL(12, 2);
        
      SELECT 
      	@nconta = nconta, 
      	@dtmov = dtmov,
        @valor = valor,
        @operacao = operacao
      FROM inserted;

      SELECT @saldo_atual = saldo 
      FROM contacorrente
      WHERE nconta = @nconta;
      
      IF (@operacao != 'D' AND @operacao != 'S')
        BEGIN
          RAISERROR('operação inválida', 16, 1);
          ROLLBACK TRANSACTION;
          RETURN;
        END;
       
      IF @operacao != 'S'
      	RETURN;

      IF (@saldo_atual - @valor < 0)
        BEGIN
          RAISERROR('saldo final é menor que zero', 16, 1);
          ROLLBACK TRANSACTION;
          RETURN;
        END;


      UPDATE contacorrente
      SET saldo = saldo - @valor
      WHERE nconta = @nconta;


      INSERT INTO operacao (operacao, valor, dtoperacao) 
      VALUES (@operacao, @valor, @dtmov);


      INSERT INTO auditoria (funcionario, operacao, valor, dtoperacao) 
      VALUES ('Matheus', @operacao, @valor, @dtmov);
    END;
END;

SELECT * FROM contacorrente WHERE nconta = 205467;
INSERT INTO movimentocc (nconta, dtmov, valor, operacao) VALUES (205467, '2022-10-15 15:30:00', 500, 'S');
INSERT INTO movimentocc (nconta, dtmov, valor, operacao) VALUES (205467, '2022-10-15 15:35:00', 500, 'K'); -- erro!
SELECT * FROM contacorrente WHERE nconta = 205467;

-- estorno de movimentação
CREATE TRIGGER tr_mov_estorno
ON movimentocc
AFTER DELETE
AS
BEGIN
  IF (SELECT COUNT(*) FROM deleted) = 1
    BEGIN
    	DECLARE
      	@nconta 	    INT,
        @dtmov 		    DATETIME,
        @valor 		    DECIMAL(12, 2),
        @operacao     CHAR,
        @saldo_atual  DECIMAL(12, 2);
        
      SELECT 
      	@nconta = nconta, 
      	@dtmov = dtmov,
        @valor = valor,
        @operacao = operacao
      FROM deleted;

      SELECT @saldo_atual = saldo 
      FROM contacorrente
      WHERE nconta = @nconta;
      
      IF (@operacao != 'D' AND @operacao != 'S')
        BEGIN
          RAISERROR('operação inválida', 16, 1);
          ROLLBACK TRANSACTION;
          RETURN;
        END;
       
      IF @operacao = 'S'
        BEGIN
          UPDATE contacorrente
          SET saldo = saldo + @valor
          WHERE nconta = @nconta;

          INSERT INTO auditoria (funcionario, operacao, valor, dtoperacao) 
          VALUES ('Matheus', 'D', @valor, @dtmov);

          INSERT INTO operacao (operacao, valor, dtoperacao) 
          VALUES ('D', @valor, @dtmov);

          RETURN;
        END;

      UPDATE contacorrente
      SET saldo = saldo - @valor
      WHERE nconta = @nconta;

      INSERT INTO auditoria (funcionario, operacao, valor, dtoperacao) 
      VALUES ('Matheus', 'S', @valor, @dtmov);

      INSERT INTO operacao (operacao, valor, dtoperacao) 
      VALUES ('S', @valor, @dtmov);
    END;
END;

SELECT * FROM contacorrente WHERE nconta = 205467;
INSERT INTO movimentocc (nconta, dtmov, valor, operacao) VALUES (205467, '2022-10-15 15:30:00', 123111, 'D');
DELETE FROM movimentocc WHERE valor = 123111;
SELECT * FROM contacorrente WHERE nconta = 205467;

-- impedir exclusão de conta-correntes
CREATE TRIGGER tr_rb_cc
ON contacorrente
FOR DELETE
AS
BEGIN
  IF (SELECT COUNT(*) FROM deleted) = 1
    BEGIN
      RAISERROR('conta-correntes não podem ser deletadas', 16, 1);
      ROLLBACK TRANSACTION;
    END
END;

DELETE FROM contacorrente WHERE nconta = 139911;
