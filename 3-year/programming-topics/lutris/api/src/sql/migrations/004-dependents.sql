CREATE TABLE dependents (
	idDependent INT PRIMARY KEY IDENTITY(1,1),
	nameDependent VARCHAR(40) NOT NULL,
	birth DATE NOT NULL,
	idEmployee INT NOT NULL
);

ALTER TABLE dependents ADD CONSTRAINT FK_Dependent_EmployeeID FOREIGN KEY (idEmployee) REFERENCES employees(idEmployee) ON DELETE CASCADE;
