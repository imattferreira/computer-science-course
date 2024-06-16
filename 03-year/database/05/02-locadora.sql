CREATE DATABASE locadora;

USE locadora;

-- criação das tabelas
CREATE TABLE locacoes (
  id              INT                     IDENTITY(1, 1) PRIMARY KEY,
  iduser          INT           NOT NULL,
  idcarro         INT           NOT NULL,
  valor           DECIMAL(8, 2) NOT NULL,
  formapagamento  VARCHAR(10)   NOT NULL,
  idvendedor      INT           NOT NULL,
  datacriacao     DATETIME      NOT NULL,
  datafinalizacao DATETIME,

  FOREIGN KEY (iduser) REFERENCES usuarios(id),
  FOREIGN KEY (idcarro) REFERENCES carros(id),
  FOREIGN KEY (idvendedor) REFERENCES vendedores(id)
);

CREATE TABLE locacoes_auditoria (
  id              INT                   IDENTITY(1, 1) PRIMARY KEY,
  iduser          INT      NOT NULL,
  idcarro         INT      NOT NULL,
  idpagamento     INT      NOT NULL,
  idvendedor      INT      NOT NULL,
  datacriacao     DATETIME NOT NULL,
  datafinalizacao DATETIME,

  FOREIGN KEY (iduser) REFERENCES usuarios(id),
  FOREIGN KEY (idcarro) REFERENCES carros(id),
  FOREIGN KEY (idpagamento) REFERENCES pagamentos(id),
  FOREIGN KEY (idvendedor) REFERENCES vendedores(id)
);

CREATE TABLE usuarios (
  id              INT                     IDENTITY(1, 1) PRIMARY KEY,
  nome            VARCHAR(100)  NOT NULL,
  email           VARCHAR(100)  NOT NULL,
  ultima_locacao  DATETIME
);

CREATE TABLE vendedores (
  id          INT                       IDENTITY(1, 1) PRIMARY KEY,
  nome        VARCHAR(100)  NOT NULL,
  totalvendas INT           NOT NULL,
  datacriacao DATETIME      NOT NULL
);

CREATE TABLE pagamentos (
  id              INT                     IDENTITY(1, 1) PRIMARY KEY,
  valor           DECIMAL(8, 2) NOT NULL,
  formapagamento  VARCHAR(10)   NOT NULL,
  datacriacao     DATETIME      NOT NULL
);

CREATE TABLE pagamentos_auditoria (
  id              INT                     IDENTITY(1, 1) PRIMARY KEY,
  valor           DECIMAL(8, 2) NOT NULL,
  status          VARCHAR(30)   NOT NULL,
  formapagamento  VARCHAR(10)   NOT NULL,
  datacriacao     DATETIME      NOT NULL
);

CREATE TABLE carros (
  id              INT                       IDENTITY(1, 1) PRIMARY KEY,
  modelo          VARCHAR(100)  NOT NULL,
  ano             INT           NOT NULL,
  status          VARCHAR(20)   NOT NULL,
  datacriacao     DATETIME      NOT NULL,
  dataatualizacao DATETIME      NOT NULL
);

CREATE TABLE carros_auditoria (
  id          INT                   IDENTITY(1, 1) PRIMARY KEY,
  idcarro     INT         NOT NULL,
  status      VARCHAR(20) NOT NULL,
  datacriacao DATETIME    NOT NULL,

  FOREIGN KEY (idcarro) REFERENCES carros(id)
);

-- triggers
CREATE TRIGGER tr_desmembrar_locacao
ON locacoes
AFTER INSERT
AS
BEGIN
  DECLARE
    @iduser INT,
    @idcarro INT,
    @idpagamento INT,
    @valor  DECIMAL(8, 2),
    @formapagamento VARCHAR(10),
    @idvendedor INT,
    @datacriacao DATETIME;

  SELECT
    @iduser = iduser,
    @idcarro = idcarro,
    @valor = valor,
    @formapagamento = formapagamento,
    @idvendedor = idvendedor,
    @datacriacao = datacriacao
  FROM inserted;

  BEGIN TRANSACTION
    UPDATE usuarios
      SET ultima_locacao = @datacriacao
      WHERE id = @iduser;

    UPDATE vendedores 
      SET totalvendas = totalvendas + 1
      WHERE id = @idvendedor;

    UPDATE carros
      SET status = 'EM USO', dataatualizacao = @datacriacao
      WHERE id = @idcarro;

    INSERT INTO pagamentos (
      valor,
      formapagamento,
      datacriacao
    ) VALUES (
      @valor,
      @formapagamento,
      @datacriacao
    );

    SELECT @idpagamento = id 
    FROM pagamentos 
    WHERE datacriacao = @datacriacao AND valor = @valor;

    INSERT INTO pagamentos_auditoria (
      valor,
      status,
      formapagamento,
      datacriacao
    ) VALUES (
      'FEITO',
      @valor,
      @formapagamento,
      @datacriacao
    );

    INSERT INTO carros_auditoria (
      idcarro,
      status,
      datacriacao
    ) VALUES (
      @idcarro,
      'EM USO',
      @datacriacao
    );

    INSERT INTO locacoes_auditoria (
      iduser,
      idcarro,
      idpagamento,
      idvendedor,
      datacriacao
    ) VALUES (
      @iduser,
      @idcarro,
      @idpagamento,
      @idvendedor,
      @datacriacao
    );
  COMMIT;
END;

CREATE TRIGGER tr_atualizar_desmembramento_locacao
ON locacoes
AFTER UPDATE
AS
BEGIN
  DECLARE
    @iduser INT,
    @idcarro INT,
    @idpagamento INT,
    @valor  DECIMAL(8, 2),
    @formapagamento VARCHAR(10),
    @idvendedor INT,
    @datacriacao DATETIME;

  SELECT
    @iduser = iduser,
    @idcarro = idcarro,
    @valor = valor,
    @formapagamento = formapagamento,
    @idvendedor = idvendedor,
    @datacriacao = datacriacao
  FROM inserted;

  BEGIN TRANSACTION
    UPDATE carros
      SET status = 'EM USO', dataatualizacao = @datacriacao
      WHERE id = @idcarro;

    INSERT INTO pagamentos (
      valor,
      formapagamento,
      datacriacao
    ) VALUES (
      @valor,
      @formapagamento,
      @datacriacao
    );

    SELECT @idpagamento = id 
    FROM pagamentos 
    WHERE datacriacao = @datacriacao AND valor = @valor;

    INSERT INTO pagamentos_auditoria (
      valor,
      status,
      formapagamento,
      datacriacao
    ) VALUES (
      'FEITO',
      @valor,
      @formapagamento,
      @datacriacao
    );

    INSERT INTO carros_auditoria (
      idcarro,
      status,
      datacriacao
    ) VALUES (
      @idcarro,
      'EM USO',
      @datacriacao
    );

    INSERT INTO locacoes_auditoria (
      iduser,
      idcarro,
      idpagamento,
      idvendedor,
      datacriacao
    ) VALUES (
      @iduser,
      @idcarro,
      @idpagamento,
      @idvendedor,
      @datacriacao
    );
  COMMIT;
END;

CREATE TRIGGER tr_deletar_locacao
ON locacoes
AFTER DELETE
AS
BEGIN
  DECLARE
    @iduser INT,
    @idcarro INT,
    @idpagamento INT,
    @valor DECIMAL(8, 2),
    @formapagamento VARCHAR(10),
    @idvendedor INT,
    @datacriacao DATETIME,
    @now DATETIME = GETDATE();

  SELECT
    @iduser = iduser,
    @idcarro = idcarro,
    @valor = valor,
    @formapagamento = formapagamento,
    @idvendedor = idvendedor,
    @datacriacao = datacriacao
  FROM deleted;

  BEGIN TRANSACTION
    UPDATE carros
      SET status = 'Aguardando', dataatualizacao = @now
      WHERE id = @idcarro;

    SELECT @idpagamento = idpagamento
    FROM locacoes_auditoria
    WHERE @iduser = iduser AND @idcarro = idcarro AND @datacriacao = datacriacao;

		DELETE FROM pagamentos WHERE id = @idpagamento;

    INSERT INTO pagamentos_auditoria (
      valor,
      status,
      formapagamento,
      datacriacao
    ) VALUES (
      @valor,
      'CANCELADO',
      @formapagamento,
      @now
    );

    INSERT INTO carros_auditoria (
      idcarro,
      status,
      datacriacao
    ) VALUES (
      @idcarro,
      'Aguardando',
      @now
    );

    INSERT INTO locacoes_auditoria (
      iduser,
      idcarro,
      idpagamento,
      idvendedor,
      datacriacao
    ) VALUES (
      @iduser,
      @idcarro,
      @idpagamento,
      @idvendedor,
      @now
    );
  COMMIT;
END;

-- seeders
INSERT INTO carros (
  modelo,
  ano,
  status,
  datacriacao,
  dataatualizacao
) VALUES 
('Fiat Uno', 1993, 'Aguardando', GETDATE(), GETDATE()),
('Honda Fit', 2017, 'Aguardando', GETDATE(), GETDATE()),
('Ford Focus PowerShift', 2020, 'Aguardando', GETDATE(), GETDATE());

SELECT * FROM carros;

INSERT INTO vendedores (
  nome,
  totalvendas,
  datacriacao
) VALUES 
('Robinho', 0, GETDATE()), 
('Melissa', 0, GETDATE());

SELECT * FROM vendedores;

INSERT INTO usuarios (
  nome,
  email
) VALUES 
('Esther', 'esther@email.com'), 
('Carlos', 'carlos@email.com');

SELECT * FROM usuarios;

INSERT INTO locacoes (
  iduser,
  idcarro,
  valor,
  formapagamento,
  idvendedor,
  datacriacao
) VALUES (
  1,
  2,
  780,
  'CREDITO',
  1,
  GETDATE()
);
INSERT INTO locacoes (
  iduser,
  idcarro,
  valor,
  formapagamento,
  idvendedor,
  datacriacao
) VALUES (
  2,
  1,
  780,
  'CREDITO',
  2,
  GETDATE(),
);

SELECT * FROM locacoes;
SELECT * FROM locacoes_auditoria;
SELECT * FROM usuarios;
SELECT * FROM vendedores;
SELECT * FROM pagamentos;
SELECT * FROM pagamentos_auditoria;
SELECT * FROM carros;
SELECT * FROM carros_auditoria;

UPDATE locacoes
  SET valor = 1000
  WHERE id = 1;

SELECT * FROM locacoes;
SELECT * FROM locacoes_auditoria;
SELECT * FROM usuarios;
SELECT * FROM vendedores;
SELECT * FROM pagamentos;
SELECT * FROM pagamentos_auditoria;
SELECT * FROM carros;
SELECT * FROM carros_auditoria;

DELETE FROM locacoes WHERE id = 2;