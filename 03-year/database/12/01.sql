create database bkp_tests;

use bkp_tests;

create table veterinarios (
	id int unique,
  nome varchar(20),
  tel varchar(12)
);

create table funcionarios (
	id int unique,
  nome varchar(20),
  cargo varchar(20)
);

create table proprietarios (
	id int unique,
  nome varchar(40),
  tel varchar(12),
  email varchar(40),
  idpet int
);

create table pets (
	id int unique,
  nome varchar(20),
  raca varchar(20),
  idade int
);

create table convenios (
	id int unique,
  nome varchar(20),
  desconto decimal(7,2)
);

create table medicamentos (
	id int unique,
  nome varchar(20),
  valor decimal(7,2)
);

create table exames (
	id int unique,
  nome varchar(20),
  valor decimal(7,2)
);

create table consultas (
  id int unique,
	dataconsulta datetime,
  idveterinario int,
  idfuncionario int,
  idproprietario int,
  idpet int,
  idconvenio int,
  idmedicamento int,
  idexame int,
  valor decimal(7,2)
);

insert into veterinarios values (1, 'Roberta', '234234234234');
insert into veterinarios values (2, 'Gabriela', '232526436463');
insert into veterinarios values (3, 'Arthur', '242342412311');
insert into veterinarios values (4, 'Maicon', '423423324255');
insert into veterinarios values (5, 'Valéria', '746435453');

insert into funcionarios values (1, 'Renata', 'Secretária');
insert into funcionarios values (2, 'Eduardo', 'Enfermeiro');
insert into funcionarios values (3, 'Alita', 'Secretária');
insert into funcionarios values (4, 'Bruno', 'Atendimento');
insert into funcionarios values (5, 'Bruna', 'Atendimento');

insert into pets values (1, 'Thor', 'Border Collie', 1);
insert into pets values (2, 'Floquinhos', 'Dálmata', 8);
insert into pets values (3, 'Bidu', 'Schnauzer', 18);
insert into pets values (4, 'Lilica', 'Akita', 2);
insert into pets values (5, 'Totó', 'Caramelo', 5);

insert into proprietarios values (1, '324242342', 'Osorio', 'osorio@email.com', 1);
insert into proprietarios values (2, '53453534', 'Roberta', 'roberta@email.com', 2);
insert into proprietarios values (3, '3423252', 'Andrey', 'andreygames@email.com', 3);
insert into proprietarios values (4, '131423524', 'Joziane', 'jojobarrafunda@email.com', 4);
insert into proprietarios values (5, '34525446', 'Clayton', 'clayuotondaleste@email.com', 5);

insert into convenios values (1, 'QualiSaude', 0.15);
insert into convenios values (2, 'Saude Sem Limites', 0.05);
insert into convenios values (3, 'VivePraValer', 0.3);
insert into convenios values (4, 'UniPlan', 0.75);
insert into convenios values (5, 'Santa Saude', 0.9);

insert into medicamentos values (1, 'Acetilcisteína', 120);
insert into medicamentos values (2, 'Cetorolaco', 18);
insert into medicamentos values (3, 'Gatifloxacino', 10);
insert into medicamentos values (4, 'Ganciclovir', 60);
insert into medicamentos values (5, 'Pravastatina', 32.5);

insert into exames values (1, 'Sangue', 120);
insert into exames values (2, 'Ressonância Torácica', 750);
insert into exames values (3, 'Urina', 150);
insert into exames values (4, 'Ultrassonografia', 500);
insert into exames values (5, 'Raio X', 300);

insert into consultas values (1, getdate(), 1, 1, 1, 1, 1, 1, 1, 500);
insert into consultas values (2, getdate(), 1, 2, 1, 1, 1, 1, 3, 400);
insert into consultas values (3, getdate(), 1, 3, 2, 2, 3, 2, 5, 487);
insert into consultas values (4, getdate(), 2, 4, 3, 3, 1, 3, 2, 550);
insert into consultas values (5, getdate(), 5, 5, 1, 1, 1, 4, 1, 870);
insert into consultas values (6, getdate(), 4, 5, 4, 4, 2, 5, 4, 543);
insert into consultas values (7, getdate(), 3, 4, 3, 3, 1, 1, 2, 800);
insert into consultas values (8, getdate(), 2, 2, 2, 2, 3, 4, 3, 642);
insert into consultas values (9, getdate(), 1, 3, 3, 3, 1, 3, 5, 699);
insert into consultas values (10, getdate(), 4, 1, 2, 2, 3, 2, 1, 520);
insert into consultas values (11, getdate(), 5, 4, 4, 4, 2, 1, 2, 538);

create table aluno (
	id int,
	nome varchar(40),
	cidade varchar(20)
);

create table curso (
	id int,
	nome varchar(40),
	mensalidade decimal(7,2)
);

create table disciplina (
	id int,
	nome varchar(40),
	creditos int
);

create table professor (
	id		int,
	nome	varchar(40),
	celular varchar(20)
);

create table matricula (
	id int,
	dtmatricula datetime,
	idaluno int,
	iddisciplina int,
	idcurso int,
	idprofessor int,
	situacao varchar(1)
);

insert into aluno values (1, 'Roberta', 'Lenções Paulista');
insert into aluno values (2, 'Ary', 'Weber Paulista');
insert into aluno values (3, 'Roberson', 'Floarianópolis');

insert into curso values (1, 'Ciência da Computação', 240.00);
insert into curso values (2, 'Medicina', 145.00);
insert into curso values (3, 'Fisioterapia', 385.00);
insert into curso values (4, 'Direito', 280.00);
insert into curso values (5, 'Engenharia Civil', 300.00);

insert into disciplina values (1, 'Noções de Direito', 30);
insert into disciplina values (2, 'Eletrônica', 30);
insert into disciplina values (3, 'Corpo Humano em Detalhes', 80);
insert into disciplina values (4, 'Javascript para Experts', 60);
insert into disciplina values (5, 'Formas de Controlar o Paciente', 40);
insert into disciplina values (6, 'Código Penal Americano', 50);

insert into professor values (1, 'Adrielson', '1232422342');
insert into professor values (2, 'Maria', '4542442424');
insert into professor values (3, 'Josefin', '34224524424');

insert into matricula values (1, GETDATE(), 1, 3, 3, 1, 1);
insert into matricula values (2, GETDATE(), 2, 1, 4, 2, 1);
insert into matricula values (3, GETDATE(), 3, 1, 2, 2, 1);

CREATE VIEW vw_xyz AS
SELECT * FROM aluno AS a 
WHERE a.nome LIKE 'Rober%' 
AND a.cidade IN ('Lenções Paulista', 'Bauru', 'Piratininga', 'Piracicaba');

CREATE VIEW vw_total_mensalidades_por_aluno AS
SELECT 
a.nome,
c.mensalidade as 'total mensalidade'
FROM matricula as m
INNER JOIN aluno as a ON a.id = m.idaluno
INNER JOIN curso as c ON c.id = m.idcurso
GROUP BY a.nome, c.mensalidade 
HAVING c.mensalidade > 300;

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

-- BACKUP
backup database bkp_tests to disk='/Users/matheusferreira/www/sql/20-05-2024__0.bak';

create table users (
  id int unique,
  nome varchar(20),  
);

insert into users values (10, 'Eva'), (12, 'Esther');

drop database bkp_tests;

backup database bkp_tests to disk='/Users/matheusferreira/www/sql/20-05-2024__1.bak' with differencial;

restore database bkp_tests from disk='/Users/matheusferreira/www/sql/20-05-2024__1.bak'

SELECT name, create_date FROM sys.tables;
SELECT name, create_date FROM sys.views;
SELECT name, create_date FROM sys.procedures;
SELECT o.name, o.create_date
FROM sys.triggers tr
JOIN sys.objects o ON tr.object_id = o.object_id;

drop table users;

restore database bkp_tests from disk='/Users/matheusferreira/www/sql/20-05-2024__1.bak' with replace;

select * from users;