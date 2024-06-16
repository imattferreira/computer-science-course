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
CREATE PROCEDURE sp_registra_venda
	@produto_id INT,
	@cliente_id INT,
	@vendedor_id INT,
	@valor DECIMAL(8, 2)
AS
	DECLARE 
		@limite_credito DECIMAL(8, 2) = -1,
		@estoque INT = -1,
		@data_venda DATETIME = GETDATE();

	SELECT limite_credito = @limite_credito FROM clientes WHERE id = @cliente_id;

	IF @limite_credito < @valor
		BEGIN
			RAISERROR('limite de credito invalido', 16, 1);
			RETURN;
		END

	SELECT estoque = @estoque FROM produtos WHERE id = @produto_id;

	IF @estoque < 1
		BEGIN
			RAISERROR('produto sem estoque', 16, 1);
			RETURN;
		END

	INSERT INTO vendas (cliente_id, vendedor_id, valor, data_venda) VALUES (@cliente_id, @vendedor_id, @valor, @data_venda);
	SELECT * FROM view_vendas;
	SELECT * FROM view_clientes;

EXEC sp_registra_venda
@produto_id = 1,
@cliente_id = 1,
@vendedor_id = 1,
@valor = 1200;