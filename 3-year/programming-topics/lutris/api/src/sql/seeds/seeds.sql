INSERT INTO sectors (nameSector, launchDate)
VALUES 
    ('Financeiro', '1990-02-15'),
    ('RH', '1992-07-20'),
    ('TI', '1995-05-10'),
    ('Marketing', '1998-09-30'),
    ('Operações', '2000-03-25'),
    ('Vendas', '2003-11-12'),
    ('Logística', '2005-08-18'),
    ('Produção', '2008-04-02'),
    ('Jurídico', '2010-10-22'),
    ('Qualidade', '2013-12-05');

INSERT INTO units (city, launchDate)
VALUES 
    ('São Paulo', '1988-01-01'),
    ('Rio de Janeiro', '1990-03-15'),
    ('Belo Horizonte', '1993-06-20'),
    ('Brasília', '1996-08-10'),
    ('Salvador', '1999-12-30'),
    ('Curitiba', '2002-05-25'),
    ('Fortaleza', '2004-09-12'),
    ('Recife', '2007-01-18'),
    ('Porto Alegre', '2009-07-02'),
    ('Manaus', '2012-11-22');

INSERT INTO employees (nameEmployee, birth, idSector, idUnit, idImage)
VALUES 
    ('João Silva', '1980-05-20', 1, 1, 1),
    ('Maria Santos', '1985-10-12', 2, 2, 2),
    ('Pedro Oliveira', '1990-03-25', 3, 3, 3),
    ('Ana Costa', '1988-07-30', 4, 4, 4),
    ('Carlos Souza', '1983-01-15', 5, 5, 5),
    ('Fernanda Pereira', '1987-04-18', 6, 6, 6),
    ('Rafaela Almeida', '1992-09-05', 7, 7, 7),
    ('Lucas Fernandes', '1982-12-10', 8, 8, 8),
    ('Juliana Lima', '1989-06-08', 9, 9, 9),
    ('Gabriel Martins', '1995-08-03', 10, 10, 10);


INSERT INTO dependents (nameDependent, birth, idEmployee)
VALUES 
    ('Pedro Silva', '2010-03-05', 1),
    ('Laura Santos', '2015-07-20', 2),
    ('Mariana Oliveira', '2012-11-15', 3),
    ('Bruno Costa', '2008-04-10', 4),
    ('Mateus Souza', '2011-06-25', 5),
    ('Isabela Pereira', '2017-09-18', 6),
    ('Luiza Almeida', '2019-01-05', 7),
    ('Gustavo Fernandes', '2014-10-30', 8),
    ('Carolina Lima', '2009-08-12', 9),
    ('Eduarda Martins', '2018-12-01', 10);
