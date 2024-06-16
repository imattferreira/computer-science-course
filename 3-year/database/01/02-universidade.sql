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

select * from aluno;
select * from curso;
select * from disciplina;
select * from matricula;
select * from professor;

-- inner join --
select m.id as matricula, c.nome as curso, a.nome, c.mensalidade
from matricula as m
inner join curso as c on m.idcurso = c.id
inner join aluno as a on m.idaluno = a.id;

-- left join --
select m.id as matricula, c.nome as curso, p.nome as professor, d.nome as disciplina
from matricula as m
left join curso as c on m.idcurso = c.id
left join disciplina as d on m.iddisciplina = d.id
left join professor as p on m.idprofessor = p.id;

-- right join --
select c.nome as curso, m.idaluno as aluno_id, m.id as matricula, c.mensalidade
from matricula as m
right join curso as c on m.idcurso = c.id;

-- full outer join --
select m.id as matricula, c.nome as curso, p.nome as professor, d.nome as disciplina
from matricula as m
full outer join curso as c on m.idcurso = c.id
full outer join disciplina as d on m.iddisciplina = d.id
full outer join professor as p on m.idprofessor = p.id;