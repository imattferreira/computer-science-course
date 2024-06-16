use livraria;

-- create tables --
create table editora (
	id int primary key,
  nome varchar(40)
);

create table autor (
	id int primary key,
  nome varchar(40),
  cpf varchar(40) unique,
  cidade varchar(20)
);

create table categoria (
	id int primary key,
  nome varchar(20)
);

create table precos (
	id int primary key,
  preco decimal(7,2),
  frete decimal(7,2)
);

create table livro (
	id int primary key,
  nome varchar(40),
  ideditora int,
  idautor int,
  idcategoria int,
  idprecos int
  
  foreign key (ideditora) references editora(id),
  foreign key (idautor) references autor(id),
  foreign key (idcategoria) references categoria(id),
  foreign key (idprecos) references precos(id)
);

-- populate tables (seeders) --
insert into editora values (1, 'Relevant');
insert into editora values (2, 'imaginary');

insert into autor values (1, 'Ruth Heldenberg', '3242668787', 'Uberlândia');
insert into autor values (2, 'Mario Alvares de Almeida', '4423322342', 'Pratânia');
insert into autor values (3, 'Berenice Stingray', '123131', 'Franco da Rocha');

insert into categoria values (1, 'Ficção');
insert into categoria values (2, 'Aventura');
insert into categoria values (3, 'Poesias');

insert into precos values (1, 50.00, 20.00);
insert into precos values (2, 230.50, 10.25);
insert into precos values (3, 88.73, 0.00);
insert into precos values (4, 53.52, 25.30);
insert into precos values (5, 12.00, 30.00);

insert into livro values (1, 'Poesias do Amanhã', 1, 3, 3, 1);
insert into livro values (2, 'As Aventuras de Josefina', 2, 2, 2, 2);
insert into livro values (3, 'Hora!', 1, 2, 2, 3);
insert into livro values (4, 'O Obscuro', 1, 1, 1, 4);
insert into livro values (5, 'A Luz Imaginada', 1, 1, 1, 5);

select * from editora;
select * from autor;
select * from categoria;
select * from precos;
select * from livro;

-- inner join --
select l.nome as livro, a.nome as autor, c.nome as categoria, e.nome as editora
from livro as l
inner join autor as a on l.idautor = a.id
inner join categoria as c on l.idcategoria = c.id
inner join editora as e on l.ideditora = e.id
