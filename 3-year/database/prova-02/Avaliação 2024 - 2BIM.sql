-- AVALIAÇÃO - SGBD - 2º Bimestre - 06/06/2024
-- Leia a prova com atenção e analise atentamente as tabelas.
-- Desenvolva SOMENTE o que for solicitado no enunciado do exercício.
-- PROIBIDO editar ou apagar o enunciado.
-- Desenvolva o script SEMPRE abaixo do enunciado.
-- NÃO é necessário inserir registros para a resolução dos exercícios, BASTA desenvolver o script.
-- Cada questão vale 2 pontos

create database prova;
use prova;

-- 1 - Utilizando INNER JOIN da tabela ALUGUEL com as demais tabelas abaixo,
--     desenvolva um script que mostre o nome do proprietário, a descrição
--     e o valor do imóvel, o nome do corretor e o nome do inquilino.

create table proprietario(
idprop		int			not null,
nome		varchar(50)	not null,
);

create table imovel(
idimov		int			not null,
descricao	varchar(40)	not null,
valor		decimal(9,2)not null,
endereco	varchar(50)	not null
);

create table corretor(
idcor	int			not null,
nome	varchar(50)	not null
);

create table inquilino(
idinq	int			not null,
nome	varchar(50)	not null
);

create table aluguel(
idprop	int	not null,
idimov	int	not null,
idcor	int	not null,
idinq	int	not null,
valor   decimal(9,2) not null
);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 1 - AQUI 
select 
  p.nome as 'proprietario',
  i.descricao,
  i.valor,
  c.nome as 'corretor',
  inq.nome as 'inquilino'
from aluguel as a
inner join proprietario as p on a.idprop = p.idprop
inner join imovel as i on a.idimov = i.idimov
inner join corretor as c on a.idcor = c.idcor
inner join inquilino as inq on a.idinq = inq.idinq;



-- 2  - Utilizando as tabelas abaixo, crie uma Trigger que quando um INSERT ocorrer na tabela MOVICC
--		TODAS as informações inseridas nessa tabela sejam gravadas na tabela auditoria

create table auditoria(
agencia		varchar(10)		not null,
nconta		varchar(10)		not null,
dtmovi		datetime		not null,
valor		decimal(9,2)	not null,
operacao	varchar(1)		not null
);

create table movicc(
agencia		varchar(10)		not null,
nconta		varchar(10)		not null,
dtmovi		datetime		not null,
valor		decimal(9,2)	not null,
operacao	varchar(1)		not null
);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 2 - AQUI 
create trigger tr_mov_auditoria
on  movicc
after insert
as 
begin
  declare
    @agencia varchar(10),
    @nconta varchar(10),
    @dtmovi datetime,
    @valor decimal(9, 2),
    @operacao varchar(1);

    select 
      @agencia = agencia,
      @nconta = nconta,
      @dtmovi = dtmovi,
      @valor = valor,
      @operacao = operacao
    from inserted;

    insert into auditoria (
      agencia,
      nconta,
      dtmovi,
      valor,
      operacao
    ) values (
      @agencia,
      @nconta,
      @dtmovi,
      @valor,
      @operacao
    );
end;


-- 3  - Desenvolver uma Stored Procedure que recebendo o código do produto e a quantidade
--		inserida, o saldo do estoque seja atualizado com a ENTRADA(+) de produtos.

create table produtos(
idpro	int			not null,
produto	varchar(40)	not null,
preco	decimal(7,2)not null,
saldo	int			not null
);

-- DESENVOLVER SCRIPT DO EXERCÍCIO 3 - AQUI
create procedure sp_atualiza_estoque
  @idpro int,
  @qdd int
as
  update produtos
  set saldo += @qdd
  where idpro = @idpro;



-- 4 - Utilizando as tabelas abaixo desenvolva o script solicitado 
--     depois de analizar as tabelas
--     nesse exercício rodar também os inserts
create table cliente(
agencia	varchar(10)	not null,
nconta	varchar(15)	not null,
cliente	varchar(40)	not null
primary key(agencia,nconta)
);
insert into cliente values('0691-9','80543-2','Lampião');

create table depositos(
cliente	varchar(40)	not null,
valor	decimal(7,2)not null
);
insert into depositos values ('Lampião',2000);

-- desenvolva uma Transact-SQL para as 2 linhas de script abaixo utilizando rollback
-- insert into cliente values('0691-X','80999-2','Virgulino');
-- update depositos set valor = 1000;
-- OBS desenvolver apenas UMA Transct-SQL

-- DESENVOLVER SCRIPT DO EXERCÍCIO 4 - AQUI
begin transaction
  insert into cliente values('0691-X','80999-2','Virgulino');
  update depositos set valor = 1000;

  if @@error = 0
  	begin
    commit;
   	end;
  else
    begin
    raiserror('algo deu errado!', 16, 1);
    rollback;
  end;


-- 5 - Considerando as tabelas abaixo
create table fone14(
cliente	varchar(40)	not null,
ddd		varchar(02)	not null,
celular	varchar(20)	not null
);
create table fone15(
cliente	varchar(40)	not null,
ddd		varchar(02)	not null,
celular	varchar(20)	not null
);
create table foneoutros(
cliente	varchar(40)	not null,
ddd		varchar(02)	not null,
celular	varchar(20)	not null
);

-- Desenvolva uma procedure que utilizando conceitos de banco de dados distribuidos receba o nome do cliente,
-- o ddd e o número do celular. Gravar os dados de DDD 14 na tabela fone14, gravar os dados de DDD 15 na tabela
-- fone 15 e gravar os dados de todos os outros DDDs na tabela foneoutros.


-- DESENVOLVER SCRIPT DO EXERCÍCIO 5 - AQUI
create procedure sp_distribui_fones
  @cliente varchar(40),
  @ddd varchar(02),
  @celular varchar(20)
as
  if @ddd = '14'
    begin
      insert into fone14 values (
        @cliente,
        @ddd,
        @celular
      );
      return;
    end;

  if @ddd = '15'
    begin
      insert into fone15 values (
        @cliente,
        @ddd,
        @celular
      );
      return;
    end;

  insert into foneoutros values (
    @cliente,
    @ddd,
    @celular
  );
  