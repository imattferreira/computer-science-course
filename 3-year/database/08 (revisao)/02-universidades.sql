use fibeduc;

-- create tables --
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

-- populate tables (seeders) --
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

-- Queries
-- 01
CREATE VIEW vw_xyz AS
SELECT * FROM aluno AS a 
WHERE a.nome LIKE 'Rober%' 
AND a.cidade IN ('Lenções Paulista', 'Bauru', 'Piratininga', 'Piracicaba');

SELECT * FROM vw_xyz;

-- 02
CREATE VIEW vw_total_mensalidades_por_aluno AS
SELECT 
a.nome,
c.mensalidade as 'total mensalidade'
FROM matricula as m
INNER JOIN aluno as a ON a.id = m.idaluno
INNER JOIN curso as c ON c.id = m.idcurso
GROUP BY a.nome, c.mensalidade 
HAVING c.mensalidade > 300;
