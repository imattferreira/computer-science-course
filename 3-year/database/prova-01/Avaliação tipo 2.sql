-- Avaliação Tipo 2

-- Execute os scripts prontos abaixo e desenvolva os scripts solicitados
-- DESENVOLVA SOMENTE o que for solicitado
-- NÃO apague o enunciado


create database avaliacaotipo2;
use avaliacaotipo2;

-- 1 - Fazer uma pesquisa em multiplas tabelas(inner join), utilizando 
--     a tabela ALUGUEL relacionada com as demais tabelas abaixo,
--     desenvolva um script que mostre o nome do proprietário, a descrição
--     e o valor do imóvel, o nome do corretor e o nome do inquilino.
--     Valor 3,0 pontos

create table proprietario(
idprop		int			not null,
nome		varchar(50)	not null,
);
insert into proprietario values(1,'Antonio');
insert into proprietario values(2,'Antenor');
insert into proprietario values(3,'Arnaldo');

create table imovel(
idimov		int			not null,
descricao	varchar(40)	not null,
valor		decimal(9,2)not null,
endereco	varchar(50)	not null
);
insert into imovel values(1,'casa',1000,'Rua 7 de Setembro');
insert into imovel values(2,'apto',900,'Rua XV de Novembro');
insert into imovel values(3,'sitio',2000,'Vale do Igapó');

create table corretor(
idcor	int			not null,
nome	varchar(50)	not null
);
insert into corretor values (10,'Florindo');
insert into corretor values (20,'Flamarion');
insert into corretor values (30,'Flaviano');

create table inquilino(
idinq	int			not null,
nome	varchar(50)	not null
);
insert into inquilino values(100, 'Robinho');

create table aluguel(
idprop	int	not null,
idimov	int	not null,
idcor	int	not null,
idinq	int	not null,
valor   decimal(9,2) not null
);
insert into aluguel values (1,1,10,100,1450);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 1 - AQUI 
select 
	p.nome as proprietario,
  im.descricao as descricao,
  a.valor as valor,
  c.nome as corretor,
	i.nome as inquilino
from aluguel as a
inner join proprietario as p on p.idprop = a.idprop
inner join inquilino as i on i.idinq = a.idinq
inner join corretor as c on c.idcor = a.idcor
inner join imovel as im on im.idimov = a.idimov;



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
insert into contacorrente values (1030,'Helvino Helvetico', 90);
insert into contacorrente values (1040,'Petronio Pedroso', 1.90);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 2 - AQUI 
drop trigger tr_movimentacao_conta;

create trigger tr_movimentacao_conta
on movimentocc
after insert
as 
begin
	declare
  	@nconta int,
    @dtmov datetime,
    @valor decimal(9, 2),
    @operacao varchar(1);

 select 
	@nconta = nconta,  
  @dtmov = dtmov,
  @valor = valor,
  @operacao = operacao
 from inserted;
 
 if (@operacao != 'D' and @operacao != 'S')
 	begin
  	raiserror('operacao invalida', 16, 1);
    rollback transaction;
    return;
  end;
  
  if (@operacao = 'S')
	  begin
    	update contacorrente
      set saldo -= @valor
      where nconta = @nconta;
    end;
   else
    begin
      update contacorrente
      set saldo += @valor
      where nconta = @nconta;
    end;
    
    insert into operacao values (@dtmov, @operacao, @valor);
    insert into auditoria values (@dtmov, 'Matheus', @operacao, @valor);
end;

insert into movimentocc values (1030, '2024-02-01 12:00:00', 100, 'S');
insert into movimentocc values (1030, '2024-02-01 12:00:00', 100, 'D');



-- 3 - Fazer uma view que mostre todos os campos da tabela imovel
-- renomeando todos os campos para melhor entendimento do usuario
-- e um filtro (where) para mostrar os imoveis com descrição casa
-- com código do imóvel entre 1 e 1000 
-- e valor do imóvel entre 500 e 5000
-- Valor 1,0 ponto

-- DESENVOLVER SCRIPT DO EXERCÍCIO 3 - AQUI 
create view vw_principais_casas as 
select 
	i.idimov as 'código',
  i.descricao as 'descrição',
  i.valor as 'valor aluguel',
	i.endereco as 'endereço'
from imovel as i 
where i.descricao = 'casa'
and i.idimov between 1 and 1000
and i.valor between 500 and 5000;

select * from vw_principais_casas;



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
insert into clientes values (10,'Anderson Antenor',300);
insert into clientes values (20,'Luisa Luizinha',1000);

create table produtos(
idpro	int			not null,
produto	varchar(40)	not null,
preco	decimal(7,2)not null,
saldo	int			not null
);
insert into produtos values (100,'Violão',999,500);
insert into produtos values (200,'Guitarra',1999,500);
insert into produtos values (300,'Bateria',2999,500);

create table venda(
idcli	int			not null,
idpro	int			not null,
preco	decimal(7,2)not null
);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 4 - AQUI 
drop procedure sp_valida_cliente;

create procedure sp_valida_cliente
 @idcli int,
 @idpro int,
 @qdd int
as
	declare 
    @limite_credito decimal(7, 2) = 0,
    @p_preco decimal(7, 2) = 0,
    @valor_total decimal(7, 2);
    
	select 
  	@limite_credito = credito 
  from clientes as c
  where c.idcli = @idcli;
  
  if (@limite_credito = 0)
    begin
    	raiserror('cliente não cadastrado!', 16, 1);
    end;
    
  select 
  	@p_preco = p.preco
  from produtos as p
  where p.idpro = @idpro;
  
  if (@p_preco = 0)
  	begin
    	raiserror('produto não cadastrado!', 16, 1);
    end;
    
  set @valor_total = @p_preco * @qdd;
  
  if (@limite_credito < @valor_total)
  	begin
    	raiserror('compra não autorizada!', 16, 1);
    end;
    
  insert into venda values (@idcli, @idpro, @valor_total);

exec sp_valida_cliente
@idcli = 10,
@idpro = 100,
@qdd = 2;

exec sp_valid_cliente
@idcli = 50,
@idpro = 100,
@qdd = 1;

exec sp_valid_cliente
@idcli = 10,
@idpro = 1,
@qdd = 10;

