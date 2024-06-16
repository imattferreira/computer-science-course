CREATE DATABASE loja;

USE loja;

-- criação das tabelas
CREATE TABLE produtos (
  id      INT                     IDENTITY(1, 1) PRIMARY KEY,
  nome    VARCHAR(100)  NOT NULL,
  estoque INT           NOT NULL,
  preco   DECIMAL(8, 2) NOT NULL,
);

CREATE TABLE vendas (
  id            INT                     IDENTITY(1, 1) PRIMARY KEY,
  usernome      VARCHAR(100)  NOT NULL,
  idproduto     INT           NOT NULL,
  valor         DECIMAL(8, 2) NOT NULL,
  vendedor      VARCHAR(100)  NOT NULL,
  datavenda     DATETIME      NOT NULL,

  FOREIGN KEY (idproduto) REFERENCES produtos(id)
);

CREATE TABLE auditoria (
  id            INT                     IDENTITY(1, 1) PRIMARY KEY,
  usernome      VARCHAR(100)  NOT NULL,
  idproduto     INT           NOT NULL,
  valor         DECIMAL(8, 2) NOT NULL,
  vendedor      VARCHAR(100)  NOT NULL,
  vendastatus   VARCHAR(20)   NOT NULL, 
  datavenda     DATETIME      NOT NULL,

  FOREIGN KEY (idproduto) REFERENCES produtos(id)
);

CREATE TABLE expedicao (
  id                INT                     IDENTITY(1, 1) PRIMARY KEY,
  idproduto         INT           NOT NULL,
  datacriacao       DATETIME      NOT NULL,
  usernome          VARCHAR(100)  NOT NULL,
  idtransportadora  INT           NOT NULL,

  FOREIGN KEY (idproduto)         REFERENCES produtos(id),
  FOREIGN KEY (idtransportadora)  REFERENCES transportadora(id),
);

CREATE TABLE comissao (
  id            INT                     IDENTITY(1, 1) PRIMARY KEY,
  vendedor      VARCHAR(100)  NOT NULL,
  comissao      DECIMAL(8, 2) NOT NULL,
);

CREATE TABLE transportadora (
  id                INT                     IDENTITY(1, 1) PRIMARY KEY,
  endereco          VARCHAR(100)  NOT NULL,
  datacriacao       DATETIME      NOT NULL,
  dataentrega       DATETIME
);

CREATE TABLE aguardandoproduto (
  id                INT                     IDENTITY(1, 1) PRIMARY KEY,
  usernome          VARCHAR(100)  NOT NULL,
  idproduto         INT           NOT NULL,
  datacriacao       DATETIME      NOT NULL,

  FOREIGN KEY (idproduto) REFERENCES produtos(id)
);

-- triggers
CREATE TRIGGER tr_valida_estoque
ON vendas
AFTER INSERT
AS
BEGIN 
  DECLARE
    @idproduto INT,
    @valor DECIMAL(8, 2),
    @vendedor VARCHAR(100),
    @datavenda DATETIME,
    @usernome VARCHAR(100),
    @endereco VARCHAR(100) = 'Rua dos Existentes 503',
    @estoque INT,
    @now DATETIME = GETDATE();

  SELECT
    @idproduto = idproduto,
    @valor = valor,
    @vendedor = vendedor,
    @datavenda = datavenda,
    @usernome = usernome
  FROM inserted;

  SELECT 
    @estoque = estoque
  FROM produtos WHERE id = @idproduto; 

  IF @estoque = 0
    BEGIN
      ROLLBACK TRANSACTION;

      INSERT INTO auditoria (
        usernome,
        idproduto,
        valor,
        vendedor,
        vendastatus,
        datavenda
      ) VALUES (
        @usernome,
        @idproduto,
        @valor,
        @vendedor,
        'SEM ESTOQUE',
        @datavenda
      );
      INSERT INTO aguardandoproduto (
        usernome,
        idproduto,
        datacriacao
      ) VALUES (
        @usernome,
        @idproduto,
        @now
      );

      RAISERROR('produto sem estoque!', 16, 1);
      RETURN;
    END;

  DECLARE @idtransportadora INT;

  UPDATE produtos
    SET estoque = @estoque - 1
    WHERE id = @idproduto;

  INSERT INTO auditoria (
    usernome,
    idproduto,
    valor,
    vendedor,
    vendastatus,
    datavenda
  ) VALUES (
    @usernome,
    @idproduto,
    @valor,
    @vendedor,
    'OK',
    @datavenda
  );
  INSERT INTO comissao (
    vendedor,
    comissao
  ) VALUES (
    @vendedor,
    (@valor * 0.05)
  );
  INSERT INTO transportadora (
    endereco,
    datacriacao
  ) VALUES (
    @endereco,
    @now
  );

  SELECT 
    @idtransportadora = id
  FROM transportadora WHERE endereco = endereco AND datacriacao = @now;

  INSERT INTO expedicao (
    idproduto,
    datacriacao,
    usernome,
    idtransportadora
  ) VALUES (
    @idproduto,
    @now,
    @usernome,
    @idtransportadora 
  );
END;

-- seeders
INSERT INTO produtos (nome, estoque, preco) VALUES 
  ('Smartphone', 10, 1500), 
  ('Camiseta', 250, 20), 
  ('Tenis', 0, 100);

SELECT * FROM produtos;

INSERT INTO vendas (
  usernome,
  idproduto,
  valor,
  vendedor,
  datavenda
) VALUES (
  'Ansdeustoson',
  3,
  90,
  'Rogerinho da Zero Nove',
  GETDATE()
);
INSERT INTO vendas (
  usernome,
  idproduto,
  valor,
  vendedor,
  datavenda
) VALUES (
  'Abgail Menezes',
  1,
  1510,
  'Rogerinho da Zero Nove',
  GETDATE()
);
INSERT INTO vendas (
  usernome,
  idproduto,
  valor,
  vendedor,
  datavenda
) VALUES (
  'Espedito Garotinho',
  1,
  1500,
  'Rogerinho da Zero Leste',
  GETDATE()
);

SELECT * FROM aguardandoproduto;
SELECT * FROM auditoria;
SELECT * FROM comissao;
SELECT * FROM expedicao;
SELECT * FROM produtos;
SELECT * FROM transportadora;
SELECT * FROM vendas;
