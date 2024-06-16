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

-- views -- 
-- União de Tabelas --
create view vw_ultimas_consultas as
  select top 10
    c.id, 
    p.nome as pet, 
    pr.nome as proprietario, 
    v.nome as veterinario,
    c.dataconsulta, 
    c.valor,
    co.nome as convenio
  from veterinarios as v
  inner join consultas as c on v.id = c.idveterinario
  inner join pets as p on p.id = c.idpet
  inner join proprietarios as pr on pr.id = c.idproprietario
  inner join convenios as co on co.id = c.idconvenio;

select * from vw_ultimas_consultas;

create view vw_fichario_pets as
  select 
    c.id, 
    v.nome as nome_veterinario, 
    v.tel as tel_veterinario, 
    f.nome as nome_funcionario, 
    f.cargo as cargo_funcionario, 
    p.nome as nome_proprietario, 
    p.email as email_proprietario, 
    pt.nome as nome_pet, 
    pt.raca as raca_pet, 
    cv.nome as convenio,
    cv.desconto as desconto_convenio,
    m.nome as medicamento,
    m.valor as valor_medicamento,
    e.nome as exame,
    e.valor as valor_exame,
    c.dataconsulta
  from consultas as c
  inner join veterinarios as v on c.idveterinario = v.id
  inner join funcionarios as f  on c.idfuncionario = f.id
  inner join proprietarios as p on c.idproprietario = p.id
  inner join convenios as cv on c.idconvenio = cv.id
  inner join medicamentos as m on c.idmedicamento = m.id
  inner join exames as e on c.idmedicamento = e.id
  inner join pets as pt on c.idpet = pt.id;

select * from vw_fichario_pets;

-- Agrupamento --
-- COUNT --
create view vw_medicamentos_mais_comuns as
  select m.nome, count(m.id) as "vezes receitado"
  from medicamentos as m
  inner join consultas as c on c.idmedicamento = m.id
  group by m.id, m.nome;
  
select * from vw_medicamentos_mais_comuns;

create view vw_veterinarios_mais_atenderam as
  select v.nome, count(v.id) as "total atendimentos"
  from veterinarios as v
  inner join consultas as c on c.idveterinario = v.id
  group by v.id, v.nome;

select * from vw_veterinarios_mais_atenderam;

-- AVG --
create view vw_avg_valor_consulta as
  select round(avg(c.valor), 2) as "valor médio"
  from consultas as c;
  
select * from vw_avg_valor_consulta;

create view vw_ticket_avg as
	select 
		round(avg(m.valor + c.valor + e.valor), 2) as "valor médio"
  from consultas as c
  inner join medicamentos as m on m.id = c.idmedicamento
  inner join exames as e on e.id = c.idexame;

select * from vw_ticket_avg;

-- SUM --
create view vw_valor_ganho as
	select 
		round(sum(m.valor + c.valor + e.valor), 2) as "total ganho"
  from consultas as c
  inner join medicamentos as m on m.id = c.idmedicamento
  inner join exames as e on e.id = c.idexame;

select * from vw_valor_ganho;

create view vw_valor_ganho_por_pet as
  select 
    p.id as pet_id, 
    sum(c.valor) as "total consultas",
    sum(e.valor) as "total exames",
    sum(m.valor) as "total medicamentos"
  from consultas as c
  inner join medicamentos as m on m.id = c.idmedicamento
  inner join exames as e on e.id = c.idmedicamento
  inner join pets as p on p.id = c.idpet
  group by p.id, p.nome;
  
select * from vw_valor_ganho_por_pet;

-- DISTINCT, BETWEEN, IN, WHERE
-- LIKE --
create view vw_vets_do_estado_23 as
  select v.id, v.nome, v.tel
  from veterinarios as v
  where v.tel like '23%';
 
select * from vw_vets_do_estado_23;

create view vw_pets_familia_border as
  select p.id, p.nome as pet, p.raca, pr.nome as proprietario, pr.tel
  from pets as p
  inner join proprietarios as pr on pr.idpet = p.id
  where p.raca like 'Border %';

select * from vw_pets_familia_border;

-- BETWEEN --
create view vw_consultas_abaixo_500_reais as
  select p.nome as pet, v.nome as veterinario, c.valor, c.dataconsulta
  from consultas as c
  inner join veterinarios as v on v.id = c.idveterinario
  inner join pets as p on p.id = c.idpet
  where c.valor between 0 and 500;
  
select * from vw_consultas_abaixo_500_reais;

create view vw_pets_com_mais_1_ano_e_menos_10_anos as
  select p.nome as pet, p.idade, pr.nome, pr.tel, pr.email
  from pets as p
  inner join proprietarios as pr on pr.idpet = p.id
  where p.idade between 0 and 10;
  
select * from vw_pets_com_mais_1_ano_e_menos_10_anos;

-- IN -- 
create view vw_pets_com_medicamentos_controlados as
  select p.nome, p.tel, p.email, m.nome as medicamento, c.dataconsulta
  from consultas as c
  inner join medicamentos as m on m.id = c.idmedicamento
  inner join proprietarios as p on p.id = c.idproprietario
  where m.nome in ('Cetorolaco', 'Gatifloxacino');
  
select * from vw_pets_com_medicamentos_controlados;

create view vw_pets_com_exames_radiografia as
  select p.nome, p.tel, p.email, e.nome as exame, c.dataconsulta
  from consultas as c
  inner join exames as e on e.id = c.idexame
  inner join proprietarios as p on p.id = c.idproprietario
  where e.nome in ('Ressonância Torácica', 'Raio X');
  
select * from vw_pets_com_exames_radiografia;

-- DISTINCT --
create view vw_nome_pets_que_ja_passaram as select distinct pets.nome from pets;

select * from vw_nome_pets_que_ja_passaram;

create view vw_cargos_funcionarios as select distinct funcionarios.cargo from funcionarios;

select * from vw_cargos_funcionarios;

-- WHERE --
create view vw_pets_medicados_com_cetorolaco as
  select p.nome, p.tel, p.email, c.dataconsulta
  from consultas as c
  inner join medicamentos as m on m.id = c.idmedicamento
  inner join proprietarios as p on p.id = c.idproprietario
  where m.nome = 'Cetorolaco';
  
select * from vw_pets_medicados_com_cetorolaco;

create view vw_ultimas_consultas_dr_roberta as
  select p.nome as pet, c.valor, c.dataconsulta
  from consultas as c
  inner join veterinarios as v on v.id = c.idveterinario
  inner join pets as p on p.id = c.idpet
  where v.nome = 'Roberta';
  
select * from vw_ultimas_consultas_dr_roberta;
