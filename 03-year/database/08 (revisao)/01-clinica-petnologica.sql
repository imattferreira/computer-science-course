-- !
use clinica;

-- create tables -- 
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

-- populate tables (seeders) --
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


-- Queries
SELECT 
  v.nome as 'veterinario',
  f.nome as 'funcionario',
  p.nome as 'proprietario',
  pe.nome as 'pet',
  pe.raca as 'raca',
  co.nome as 'convênio',
  m.nome as 'medicamento',
  e.nome as 'exame'
FROM consultas as c
INNER JOIN veterinarios as v ON c.idveterinario = v.id
INNER JOIN funcionarios as f ON c.idfuncionario = f.id
INNER JOIN proprietarios as p ON c.idproprietario = p.id
INNER JOIN pets as pe ON c.idpet = pe.id
INNER JOIN convenios as co ON c.idconvenio = co.id
INNER JOIN medicamentos as m ON c.idmedicamento = m.id
INNER JOIN exames as e ON c.idexame = e.id;
