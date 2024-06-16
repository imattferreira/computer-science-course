-- Avaliação Tipo 1
-- Execute os scripts prontos abaixo e desenvolva os scripts solicitados
-- DESENVOLVA SOMENTE o que for solicitado
-- NÃO apague o enunciado


create database avaliacaotipo1;
use avaliacaotipo1;

-- 1 - Fazer uma pesquisa em multiplas tabelas(inner join), utilizando 
--     a tabela ATENDIMENTO relacionada com as demais tabelas abaixo,
--     desenvolva um script que mostre o nome do funcionario, 
--	   o nome e a raça do pet, o serviço prestado e o nome do cliente
--     Valor 3,0 pontos

create table cliente(
idcli	int			not null,
cliente	varchar(40)	unique,
cidade	varchar(25)	not null,
estado	varchar(02)	not null
primary key(idcli)
);
insert into cliente values (10,'Pedro','Bauru','SP');
insert into cliente values (20,'Paulo','Agudos','SP');

create table animal(
idpet	int			not null,
pet		varchar(30)	not null,
sexo	varchar(01) not null,
raca	varchar(20) not null,
tipo	varchar(20)	not null,
idcli	int			not null
primary key(idpet)
foreign key(idcli) references cliente(idcli)
);
insert into animal values (100,'Fred','M','yorkshire','dog',40);
insert into animal values (200,'Mel','F','yorkshire','dog',40);

create table servico(
idserv	int			not null,
servico	varchar(40)	not null,
valor	decimal(7,2)not null
primary key(idserv)
);
insert into servico values (111,'Banho',40);
insert into servico values (112,'Tosa',70);

create table funcionario(
idfunc		int			not null,
funcinario	varchar(40)	not null,
cargo		varchar(20)	not null
primary key(idfunc)
);
insert into funcionario values (1000,'Apolonio','Veterinario');
insert into funcionario values (2000,'Berenice','Atendente');

create table atendimento(
idatend	int	identity,
idserv	int	not null,
idfunc	int	not null,
idpet	int	not null,
idcli	int not null
primary key(idatend)
foreign key(idserv) references servico(idserv),
foreign key(idfunc) references funcionario(idfunc),
foreign key(idpet)  references animal(idpet),
foreign key(idcli)	references cliente(idcli)
);
insert into atendimento values (111,1000,100,10);
insert into atendimento values (112,2000,200,20);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 1 - AQUI 





-- 2 - Utilizando as tabelas abaixo criar uma trigger que receba
-- o numero da conta corrente, a data, o valor e o código da operação
-- sendo S para Saque e D para depósito
-- Verifique o código da operação e atualize o saldo da conta corrente
-- grave os dados necessários na tabelas movimentocc, operação e auditoria
-- conforme os campos dessas tabelas.
-- NÃO é necessário verificar se o número da conta é valido
-- É necessário verificar se o código da operação é valido
-- Valor 3,0 pontos

create table contacorrente(
nconta	int			not null,  
cliente varchar(50)	not null,
saldo	decimal(9,2)not null
);

create table movimentocc(
nconta		int			not null,
dtmov		datetime	not null,
valor		decimal(9,2)not null,
operacao	varchar(1)	not null   
);

create table operacao(
dtoperacao	datetime	not null,
operacao	varchar(1)	not null,
valor		decimal(9,2)not null	
);

create table auditoria(
dtoperacao	datetime	not null,
funcionario varchar(50) not null,
operacao	varchar(1)	not null,   
valor		decimal(9,2)not null
);
insert into contacorrente values (1010,'Anderson Antenor', 5500);
insert into contacorrente values (1020,'Agnaldo Agnangelo', 700);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 2 - AQUI 





-- 3 - Fazer uma view que mostre todos os campos da tabela animal
-- renomeando todos os campos para melhor entendimento do usuario
-- e um filtro (where) para mostrar os animais machos, do tipo dog
-- da raça yorkshire e com idpet entre 80 e 2000.
-- Valor 1,0 ponto

-- DESENVOLVER SCRIPT DO EXERCÍCIO 3 - AQUI 





-- 4 - Fazer uma stored procedute onde será informado o código do cliente
-- verificar se o cliente está cadastrado. 
-- Se sim guardar em variável o limite de crédito dele, senão mensagem de cliente não cadastrado. 
-- Informar o código do produto e a quantidade vendida do produto. Verificar se o produto existe, 
-- Se sim guardar o preço do produto, calcular quantidade x preço 
-- guardar o resultado em valor a pagar. 
-- Se não, dar mensagem de produto não cadastrado. 
-- Verificar se o valor a pagar esta dentro do limite guardado do cliente. 
-- Se sim armazenar os dados na tabela vendas, se não dar mensagem de compra não autorizada.
-- Valor 3,0 pontos

create table clientes(
idcli	int			not null,
cliente	varchar(40)	not null,
credito	decimal(7,2)not null
);
insert into clientes values (1,'Weverton Simonal',300);
insert into clientes values (2,'Anita Garibaldi',1000);

create table produtos(
idpro	int			not null,
produto	varchar(40)	not null,
preco	decimal(7,2)not null,
saldo	int			not null
);
insert into produtos values (10,'Camisa',72.99,100);
insert into produtos values (20,'Sapato',121.98,100);
insert into produtos values (30,'Calça',222.30,100);

create table venda(
idcli	int			not null,
idpro	int			not null,
preco	decimal(7,2)not null
);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 4 - AQUI 


