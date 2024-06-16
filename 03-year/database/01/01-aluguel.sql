use imobiliaria;

-- create tables --
create table proprietario (
	id int,
	nome varchar(80),
);

create table imovel (
	id int,
	endereco varchar(80),
	proprietario_id int,
	cidade varchar(30)
);

create table corretor (
	id int,
	nome varchar(80)
);

create table inquilino (
	id int,
	nome varchar(80)
);

create table aluguel (
	id int,
	imovel_id int,
	corretor_id int,
	inquilino_id int,
	valor float
);

-- populate tables (seeders) --
insert into proprietario values (1, 'Gabriela');
insert into proprietario values (2, 'Heitor');
insert into proprietario values (3, 'Roberson');

insert into imovel values (1, 'Rua dos Tupiniquins', 2, 'RJ');
insert into imovel values (2, 'Rua dos Perdidos', 3, 'SP');
insert into imovel values (3, 'Rua dos Achados', 3, 'PR');
insert into imovel values (4, 'Rua Central', 1, 'AM');

insert into inquilino values (1, 'Andrey');
insert into inquilino values (2, 'Larissa');
insert into inquilino values (3, 'Casares');

insert into corretor values(1, 'Leoncio');

insert into aluguel values (1, 1, 1, 1, 1200);
insert into aluguel values (2, 2, 1, 2, 2400);
insert into aluguel values (3, 3, 1, 3, 650);

-- show all tables content --
select * from proprietario;
select * from imovel;
select * from inquilino;
select * from corretor;
select * from aluguel;

-- inner join --
select p.nome, im.endereco, im.cidade 
from proprietario as p
inner join imovel as im on p.id = im.proprietario_id;

-- left join --
select p.nome, im.cidade, a.valor
from proprietario as p
left join imovel as im on im.proprietario_id = p.id
left join aluguel as a on a.imovel_id = im.id;

-- right join --
select p.nome, im.cidade, a.valor
from proprietario as p
right join imovel as im on im.proprietario_id = p.id
right join aluguel as a on a.imovel_id = im.id;

-- full outer join --
select p.nome as proprietario, i.nome as inquilino, im.cidade
from aluguel as a
full outer join inquilino as i on i.id = a.inquilino_id
full outer join imovel as im on im.id = a.imovel_id
full outer join proprietario as p on p.id = im.proprietario_id;