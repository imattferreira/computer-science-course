CREATE DATABASE store;

USE store;

CREATE TABLE products (
  cod      IDENTITY(1, 1) PRIMARY KEY,
  name     VARCHAR(100)   NOT NULL,
  price    DECIMAL(8, 2)  NOT NULL,
  quantity INT            NOT NULL
);

CREATE TABLE clients (
  cod           IDENTITY(1, 1)  PRIMARY KEY,
  name          VARCHAR(100)    NOT NULL,
  credit_limit  DECIMAL(8, 2)   NOT NULL,
);

CREATE TABLE sales (
  cod         IDENTITY(1, 1)  PRIMARY KEY,
  client_cod  INT           NOT NULL,
  total       DECIMAL(8, 2) NOT NULL,
  created_at  DATETIME      DEFAULT GETDATE(),

  FOREIGN KEY (client_cod) REFERENCES clients(cod)
);

INSERT INTO products (name, price, quantity) VALUES 
('iPhone 15 PRO Ultra Blaster Max', 5200, 5), 
('Notebook Positivo Mega', 320, 200), 
('Teclado Suspeito', 3, 12);

INSERT INTO clients (name, credit_limit) VALUES 
('Jonas Bento', 120), 
('Mariana', 4200), 
('Esther', 50000);

INSERT INTO sales (client_cod, total) VALUES 
(1, 3), 
(3, 1);

-- A
CREATE PROCEDURE sp_update_increase_stocks
  @product_cod INT,
  @quantity INT
AS

  IF @quantity <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE cod = @product_cod)
    BEGIN
      RAISERROR('produto não encontrado!');
      RETURN;
    END;

  UPDATE products
  SET quantity += @quantity
  WHERE code = @product_cod;

EXEC sp_update_increase_stocks
@product_cod = 3
@quantity = 100

SELECT * FROM products;

-- B
CREATE PROCEDURE sp_update_decrease_stocks
  @product_cod INT,
  @quantity INT
AS

  IF @quantity <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE cod = @product_cod)
    BEGIN
      RAISERROR('produto não encontrado!');
      RETURN;
    END;

  UPDATE products
  SET quantity -= @quantity
  WHERE code = @product_cod;

EXEC sp_update_decrease_stocks
@product_cod = 1
@quantity = 2

SELECT * FROM products;

-- C
CREATE PROCEDURE sp_update_product_price
  @product_cod INT,
  @percentage DECIMAL(8, 2)
AS
  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE cod = @product_cod)
    BEGIN
      RAISERROR('produto não encontrado!');
      RETURN;
    END;

  UPDATE products
  SET price += price * @percentage
  WHERE code = @product_cod;

EXEC sp_update_product_price
@product_cod = 2
@percentage = 0.5 -- +50%

SELECT * FROM products;

-- D
CREATE PROCEDURE sp_make_sale
@client_cod INT,
@total DECIMAL(8, 2)
AS
  DECLARE 
    @credit_limit DECIMAL(8, 2);

  IF NOT EXISTS (
    SELECT COUNT(name),
    credit_limit = @credit_limit 
    FROM clients 
    WHERE cod = @client_cod
  )
    BEGIN
      RAISERROR('cliente não encontrado!');
      RETURN;
    END;

  IF @credit_limit - @total < 0
    BEGIN
      RAISERROR('cliente sem limite de crédito!');
      RETURN;
    END;


  BEGIN TRANSACTION
    UPDATE clients
    SET @credit_limit -= @total
    WHERE cod = @client_cod;

    INSERT INTO sales (client_cod, total) VALUES (@client_cod, @total);
  COMMIT;

EXEC sp_make_sale
@client_cod = 1
@total = 5200;

EXEC sp_make_sale
@client_cod = 3
@total = 5200;

-- E
CREATE PROCEDURE sp_make_automated_sale
@client_cod INT,
@product_cod INT,
@product_qty INT
AS
  DECLARE 
    @credit_limit DECIMAL(8, 2),
    @product_price DECIMAL(8, 2),
    @product_stocked_qty INT;

  IF @product_qty <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF NOT EXISTS (
    SELECT COUNT(name), 
    quantity = @product_stocked_qty, 
    price = @product_price 
    FROM products 
    WHERE cod = @product_cod
  )
    BEGIN
      RAISERROR('produto não encontrado!');
      RETURN;
    END;

  IF @product_stocked_qty < @product_qty
    BEGIN
      RAISERROR('quantidade maior que a estocada!');
      RETURN;
    END;

  IF NOT EXISTS (
    SELECT COUNT(name), credit_limit = @credit_limit 
    FROM clients 
    WHERE cod = @client_cod
  )
    BEGIN
      RAISERROR('cliente não encontrado!');
      RETURN;
    END;

  IF @credit_limit - @total < 0
    BEGIN
      RAISERROR('cliente sem limite de crédito!');
      RETURN;
    END;

  DECLARE @total INT  = @product_qty * @product_price;


  BEGIN TRANSACTION
    UPDATE clients
    SET @credit_limit -= @total
    WHERE cod = @client_cod;

    UPDATE products
    SET quantity -= @product_qty
    WHERE cod = @product_cod;

    INSERT INTO sales (client_cod, total) VALUES (@client_cod, @total);
  COMMIT;

EXEC sp_make_automated_sale
@client_cod = 1
@product_cod = 1
@product_qty = 3;

EXEC sp_make_automated_sale
@client_cod = 3
@product_cod = 1
@product_qty = 1;

-- F
CREATE TABLE stock_entries (
  cod         IDENTITY(1, 1) PRIMARY KEY,
  product_cod INT            NOT NULL,
  quantity    INT            NOT NULL,
  created_at  DATETIME       DEFAULT      GETDATE(),

  FOREIGN KEY product_cod REFERENCES products(cod)
);

CREATE PROCEDURE sp_update_increase_stocks
  @product_cod INT,
  @quantity INT
AS

  IF @quantity <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE cod = @product_cod)
    BEGIN
      RAISERROR('produto não encontrado!');
      RETURN;
    END;

  BEGIN TRANSACTION
    UPDATE products
    SET quantity += @quantity
    WHERE code = @product_cod;

    INSERT INTO stock_entries (
      product_cod, 
      quantity
    ) VALUES (
      @product_cod,
      @quantity
    );
  COMMIT;

EXEC sp_update_increase_stocks
@product_cod = 3
@quantity = 100

SELECT * FROM products;

-- G
CREATE PROCEDURE sp_show_stock_stats
AS
  SELECT 
  name as 'produto',
  quantity as 'quantidade',
  (quantity * price) as 'valor total em estoque'
  FROM products;

EXEC sp_show_stock_stats;

-- H
CREATE PROCEDURE sp_import_products
@name VARCHAR(100),
@price DECIMAL(8, 2)
@quantity INT
AS

    IF @price <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF @quantity <= 0
    BEGIN
      RAISERROR('quantidade inválida!');
      RETURN;
    END;

  IF EXISTS (SELECT COUNT(name) FROM products WHERE name = @name)
    BEGIN
      RAISERROR('produto já registrado!');
      RETURN;
    END;

  INSERT INTO products (
    name, 
    price, 
    quantity
  ) VALUES (
    @name,
    @price,
    @quantity
  );

EXEC sp_import_products
@name = 'Pen-Drive Singlelaser'
@price = 150
@quantity = 10

-- I
CREATE PROCEDURE sp_show_total_of_stock
AS
  SELECT 
  SUM(quantity) as 'total de produtos'
  SUM(quantity * price) as 'valor total em estoque'
  FROM products;

EXEC sp_show_total_of_stock;

-- J
CREATE PROCEDURE sp_cleanup_sales
  @quantity INT
AS
  DECLARE 
    cod INT,
    i INT;

  WHILE (@curr != @quantity)
  BEGIN
    SELECT TOP 1 cod = @cod FORM sales;
    DELETE FROM sales WHERE cod = @cod;
    SET @i += 1;
  END; 

EXEC sp_cleanup_sales
@quantity = 2;
