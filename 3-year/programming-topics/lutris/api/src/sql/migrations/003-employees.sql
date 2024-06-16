CREATE TABLE employees (
	idEmployee INT PRIMARY KEY IDENTITY(1,1),
	nameEmployee VARCHAR(40) NOT NULL,
	birth DATE NOT NULL,
	idSector INT, 
	idUnit INT
);

ALTER TABLE employees ADD CONSTRAINT FK_Employee_SectorID FOREIGN KEY (idSector) REFERENCES sectors(idSector) ON DELETE SET NULL;

ALTER TABLE employees ADD CONSTRAINT FK_Employee_UnitID FOREIGN KEY (idUnit) REFERENCES units(idUnit) ON DELETE SET NULL;
