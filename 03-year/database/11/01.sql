USE db1;

CREATE TABLE users (
  id              INT             IDENTITY(1, 1) PRIMARY KEY,
  name            VARCHAR(120)    NOT NULL,
  purchases_count INT             DEFAULT 0,
);

CREATE TABLE purchases (
  id              INT             IDENTITY(1, 1)  PRIMARY KEY,
  user_id         INT             NOT NULL,
  product_id      INT             NOT NULL,
  created_at      DATETIME        DEFAULT         GETDATE(),

  FOREIGN KEY user_id REFERENCES users(id),
  FOREIGN KEY product_id REFERENCES products(id),
);

CREATE TABLE products (
  id              INT             IDENTITY(1, 1) PRIMARY KEY,
  name            VARCHAR(120)    NOT NULL,
  price           DECIMAL(8, 2)   NOT NULL,
  total_purchases INT             DEFAULT 0
);

CREATE TABLE transports (
  id              INT             IDENTITY(1, 1) PRIMARY KEY,
  purchase_id     INT             NOT NULL,
  address         VARCHAR(255)    NOT NULL,
  city            VARCHAR(100)    NOT NULL,

  FOREIGN KEY purchase_id REFERENCES purchases(id)
);

-- 01
BEGIN TRANSACTION
  DELETE FROM purchases;
  DELETE FROM transports;

  UPDATE users
  SET purchases_count = 0
  WHERE id = @user_id;

  UPDATE products
  SET total_purchases = 0
  WHERE id = @product_id;

  COMMIT;

-- 02
BEGIN TRANSACTION
  DELETE FROM purchases;
  DELETE FROM transports;

  UPDATE users
  SET purchases_count = 0
  WHERE id = @user_id;

  UPDATE products
  SET total_purchases = 0
  WHERE id = @product_id;

  IF @@error = 0
    COMMIT;
  ELSE
    BEGIN
    RAISERROR('algo deu errado!', 16, 1);
    ROLLBACK;
  END;

-- 03
CREATE TRIGGER tr_up_counters
ON purchases
AFTER INSERT
AS 
BEGIN
  DECLARE
    @user_id INT,
    @product_id INT;

  SELECT 
    @user_id = user_id, 
    @product_id = product_id
  FROM inserted;

  BEGIN TRANSACTION
    UPDATE users
    SET purchases_count += 1
    WHERE id = @user_id;

    UPDATE products
    SET total_purchases += 1
    WHERE id = @product_id;

    IF @@error = 0
      COMMIT;
    ELSE
      BEGIN
      RAISERROR('algo deu errado!', 16, 1);
      ROLLBACK;
    END;
END;

-- 04
CREATE PROCEDURE sp_make_purchase
  @product_id INT,
  @user_id    INT,
  @address    VARCHAR(255),
  @city       VARCHAR(100)
AS
  IF NOT EXISTS (SELECT COUNT(name) FROM users WHERE id = @user_id)
    BEGIN
      RAISERROR('usuário não encontrado!');
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE id = @product_id)
    BEGIN
      RAISERROR('usuário não encontrado!', 16, 1);
      RETURN;
    END;

  DECLARE @purchase_id INT;

  BEGIN TRANSACTION
    INSERT INTO purchases (
      user_id, 
      product_id
    ) VALUES (
      @user_id, 
      @product_id
    );

    SET @purchase_id = SCOPE_IDENTITY();

    INSERT INTO transports (
      purchase_id, 
      address, 
      city
    ) VALUES (
      @purchase_id, 
      @address, 
      @city
    );

    UPDATE users
    SET purchases_count += 1
    WHERE id = @user_id;

    UPDATE products
    SET total_purchases += 1
    WHERE id = @product_id;

    IF @@error = 0
      COMMIT;
    ELSE
      BEGIN
      RAISERROR('algo deu errado!', 16, 1);
      ROLLBACK;
    END;

EXEC sp_make_purchase
@product_id = 1
@user_id = 1
@address = 'Travessa Perdida'
@city = 'São Jose do Marianinho';

-- 05
CREATE FUNCTION purchases_from_last_week()
RETURNS TABLE AS
  RETURN (
    SELECT z
      u.name as 'usuário',
      pr.name as 'produto',
      pr.price as 'valor',
      p.created_at as 'realizado em',
    FROM purchases as p
    LEFT JOIN users as u ON p.user_id = u.id
    LEFT JOIN products as pr ON p.product_id = pr.id
    WHERE created_at <= DATEADD(WEEK, -1, GETDATE())
  );

CREATE FUNCTION purchases_of_product(@product_name VARCHAR(120))
RETURNS TABLE AS
  RETURN (
    SELECT z
      u.name as 'usuário',
      p.created_at as 'realizado em',
    FROM purchases as p
    LEFT JOIN users as u ON p.user_id = u.id
    LEFT JOIN products as pr ON p.user_id = pr.id
    WHERE pr.name = @product_name
  );

SELECT * FROM purchases_from_last_week();
SELECT * FROM purchases_of_product('iphone');
