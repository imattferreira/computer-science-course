CREATE DATABASE produtos;

USE produtos;

-- criação de tabelas
CREATE TABLE pedidos_produtos (
  id              INT												IDENTITY(1, 1)            PRIMARY KEY,
  nome_produto    VARCHAR(255)    NOT NULL,
  preco_unitario  DECIMAL(12, 2)  NOT NULL,
  tipo            VARCHAR(255),
  quantidade      INT             NOT NULL,
  nome_empresa    VARCHAR(255)    NOT NULL,
  cnpj_empresa    VARCHAR(255)    NOT NULL,
  nome_cliente    VARCHAR(255)    NOT NULL,
  cpf_cliente     VARCHAR(255)    NOT NULL,
  cod_pedido      INT             NOT NULL,
  observacoes     VARCHAR(255),
  data_pedido     DATETIME        NOT NULL,
  data_entrega    DATETIME,
);

CREATE TABLE empresas (
  id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  cnpj VARCHAR(255) NOT NULL,
);

CREATE TABLE clientes (
  id          INT												IDENTITY(1, 1)            PRIMARY KEY,
  nome        VARCHAR(255) NOT NULL,
  cpf         VARCHAR(255) NOT NULL,
);

CREATE TABLE pedidos (
  id            INT												IDENTITY(1, 1)            PRIMARY KEY,
  codigo        INT           NOT NULL,
  observacoes   VARCHAR(255),
  data_pedido   DATETIME      NOT NULL,
  data_entrega  DATETIME,
);

CREATE TABLE produtos (
  id              INT												IDENTITY(1, 1)            PRIMARY KEY,
  nome            VARCHAR(255)    NOT NULL,
  preco_unitario  DECIMAL(12, 2)  NOT NULL,
  tipo            VARCHAR(255),
);

-- triggers
CREATE TRIGGER tr_distribui_campos_pp 
ON pedidos_produtos
AFTER INSERT
AS 
BEGIN
  DECLARE
  @nome_produto   VARCHAR(255),
  @preco_unitario DECIMAL(12, 2),
  @tipo           VARCHAR(255),
  @quantidade     INT,
  @nome_empresa   VARCHAR(255),
  @cnpj_empresa   VARCHAR(255),
  @nome_cliente   VARCHAR(255),
  @cpf_cliente    VARCHAR(255),
  @cod_pedido     INT,
  @observacoes    VARCHAR(255),
  @data_pedido    DATETIME,
  @data_entrega   DATETIME;

  SELECT 
  @nome_produto = nome_produto,
  @preco_unitario = preco_unitario,
  @tipo = tipo,
  @quantidade = quantidade,
  @nome_empresa = nome_empresa,
  @cnpj_empresa = cnpj_empresa,
  @nome_cliente = nome_cliente,
  @cpf_cliente = cpf_cliente,
  @cod_pedido = cod_pedido,
  @observacoes = observacoes,
  @data_pedido = data_pedido,
  @data_entrega = data_entrega
  FROM inserted;

  BEGIN TRANSACTION
    INSERT INTO empresas (nome, cnpj) VALUES (@nome_empresa, @cnpj_empresa);

    INSERT INTO clientes (nome, cpf) VALUES (@nome_cliente, @cpf_cliente);

    INSERT INTO pedidos (
      codigo,
      observacoes,
      data_pedido,
      data_entrega
    ) VALUES (
      @cod_pedido,
      @observacoes,
      @data_pedido,
      @data_entrega
    );

    INSERT INTO produtos (
      nome,
      preco_unitario,
      tipo
    ) VALUES (
      @nome_produto,
      @preco_unitario,
      @tipo
    );

  COMMIT;
END;

-- seeders
INSERT INTO pedidos_produtos (
  nome_produto,
  preco_unitario,
  tipo,
  quantidade,
  nome_empresa,
  cnpj_empresa,
  nome_cliente,
  cpf_cliente,
  cod_pedido,
  observacoes,
  data_pedido,
  data_entrega
) VALUES (
  'Smartphone',
  2300,
  'azul',
  1,
  'Ações Brasil',
  '36238056000126',
  'Robernizelson Almeida',
  '05790406076',
  1231412,
  NULL,
  '2024-02-01 12:00:00',
  NULL
);
INSERT INTO pedidos_produtos (
  nome_produto,
  preco_unitario,
  tipo,
  quantidade,
  nome_empresa,
  cnpj_empresa,
  nome_cliente,
  cpf_cliente,
  cod_pedido,
  observacoes,
  data_pedido,
  data_entrega
) VALUES (
  'Kit Áudio e Multimídia Automotivo',
  5700,
  NULL,
  12,
  'Eletrônicos Audio e Video',
  '57471045000161',
  'Mirian Silva e Souza',
  '03030991008',
  1231412,
  NULL,
  '2024-02-01 12:00:00',
  NULL
);
INSERT INTO pedidos_produtos (
  nome_produto,
  preco_unitario,
  tipo,
  quantidade,
  nome_empresa,
  cnpj_empresa,
  nome_cliente,
  cpf_cliente,
  cod_pedido,
  observacoes,
  data_pedido,
  data_entrega
) VALUES (
  'Conjunto de Panelas Industrial',
  750,
  NULL,
  3,
  'Cozinar Peças',
  '63102443000103',
  'Rodrigo Goes',
  '38530807073',
  1231412,
  NULL,
  '2024-02-01 12:00:00',
  NULL
);

SELECT * FROM pedidos_produtos;
SELECT * FROM empresas;
SELECT * FROM clientes;
SELECT * FROM pedidos;
SELECT * FROM produtos;