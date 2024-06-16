CREATE DATABASE store1;
CREATE DATABASE store2;
CREATE DATABASE store3;

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

CREATE TABLE purchasesAI (
  id              INT             IDENTITY(1, 1)  PRIMARY KEY,
  user_id         INT             NOT NULL,
  product_id      INT             NOT NULL,
  created_at      DATETIME        DEFAULT         GETDATE(),

  FOREIGN KEY user_id REFERENCES users(id),
  FOREIGN KEY product_id REFERENCES products(id),
);

CREATE TABLE purchasesJR (
  id              INT             IDENTITY(1, 1)  PRIMARY KEY,
  user_id         INT             NOT NULL,
  product_id      INT             NOT NULL,
  created_at      DATETIME        DEFAULT         GETDATE(),

  FOREIGN KEY user_id REFERENCES users(id),
  FOREIGN KEY product_id REFERENCES products(id),
);

CREATE TABLE purchasesSZ (
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
CREATE PROCEDURE sp_make_purchase_replica
  @product_id INT,
  @user_id    INT,
  @address    VARCHAR(255),
  @city       VARCHAR(100)
AS
  IF NOT EXISTS (SELECT COUNT(name) FROM store1.dbo.users WHERE id = @user_id)
    BEGIN
      RAISERROR('usuário não encontrado!');
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM store1.dbo.products WHERE id = @product_id)
    BEGIN
      RAISERROR('usuário não encontrado!', 16, 1);
      RETURN;
    END;

  DECLARE @purchase_id INT;

  BEGIN TRANSACTION
    INSERT INTO store1.dbo.purchases (
      user_id, 
      product_id
    ) VALUES (
      @user_id, 
      @product_id
    );

    INSERT INTO store1.dbo.purchases (
      user_id, 
      product_id
    ) VALUES (
      @user_id, 
      @product_id
    );

    INSERT INTO store1.dbo.purchases (
      user_id, 
      product_id
    ) VALUES (
      @user_id, 
      @product_id
    );

    SET @purchase_id = SCOPE_IDENTITY();

    INSERT INTO store1.dbo.transports (
      purchase_id, 
      address, 
      city
    ) VALUES (
      @purchase_id, 
      @address, 
      @city
    );
    INSERT INTO store2.dbo.transports (
      purchase_id, 
      address, 
      city
    ) VALUES (
      @purchase_id, 
      @address, 
      @city
    );
    INSERT INTO store3.dbo.transports (
      purchase_id, 
      address, 
      city
    ) VALUES (
      @purchase_id, 
      @address, 
      @city
    );

    UPDATE store1.dbo.users
    SET purchases_count += 1
    WHERE id = @user_id;

    UPDATE store2.dbo.users
    SET purchases_count += 1
    WHERE id = @user_id;

    UPDATE store3.dbo.users
    SET purchases_count += 1
    WHERE id = @user_id;

    UPDATE store1.dbo.products
    SET total_purchases += 1
    WHERE id = @product_id;

    UPDATE store2.dbo.products
    SET total_purchases += 1
    WHERE id = @product_id;

    UPDATE store3.dbo.products
    SET total_purchases += 1
    WHERE id = @product_id;

    IF @@error = 0
      COMMIT;
    ELSE
      BEGIN
      RAISERROR('algo deu errado!', 16, 1);
      ROLLBACK;
    END;

EXEC sp_make_purchase_replica
@product_id = 1
@user_id = 1
@address = 'Travessa Perdida'
@city = 'São Jose do Marianinho';

-- 02
CREATE PROCEDURE sp_make_fragmented_purchase
  @product_id INT,
  @user_id    INT,
  @user_name  VARCHAR(120),
  @address    VARCHAR(255),
  @city       VARCHAR(100)
AS
  IF NOT EXISTS (SELECT COUNT(name), @user_name = name FROM users WHERE id = @user_id)
    BEGIN
      RAISERROR('usuário não encontrado!');
    END;

  IF NOT EXISTS (SELECT COUNT(name) FROM products WHERE id = @product_id)
    BEGIN
      RAISERROR('usuário não encontrado!', 16, 1);
      RETURN;
    END;

  DECLARE 
    @purchase_id INT,
    @letter CHAR(1);

  SET @letter = upper(substring(@userName));

  BEGIN TRANSACTION

	IF @letter IN ('A','B','C','D','E','F','G','H','I')
    BEGIN
      INSERT INTO purchasesAI (
        user_id, 
        product_id
      ) VALUES (
        @user_id, 
        @product_id
      );
    END;
  ELSE IF @letter IN ('J','K','L','M','N','O','P','Q','R')
    BEGIN
      INSERT INTO purchasesJR (
        user_id, 
        product_id
      ) VALUES (
        @user_id, 
        @product_id
      );
    END;
  ELSE
    BEGIN
      INSERT INTO purchasesSZ (
        user_id, 
        product_id
      ) VALUES (
        @user_id, 
        @product_id
      );
    END;

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

    IF @@error = 0
      COMMIT;
    ELSE
      BEGIN
      RAISERROR('algo deu errado!', 16, 1);
      ROLLBACK;
    END;

EXEC sp_make_fragmented_purchase
@product_id = 1
@user_id = 1
@address = 'Travessa Perdida'
@city = 'São Jose do Marianinho';
