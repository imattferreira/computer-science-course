CREATE TABLE Books (
 	isbn      INT             PRIMARY KEY,
  title     VARCHAR(255),
  author    VARCHAR(255)
 );

CREATE TABLE Students (
	ra    INT             PRIMARY KEY,
  name  VARCHAR(255)
 );

CREATE TABLE Loans (
 	 id 										INT AUTO_INCREMENT PRIMARY KEY,
   book_isbn 						  INT,
   student_ra 						INT,
   status 								ENUM('ACTIVE', 'LATE', 'FINISHED'),
 	 start_date 						DATETIME,
   expected_finish_date 	DATETIME,

   FOREIGN KEY (book_isbn) 	REFERENCES Books(isbn),
   FOREIGN KEY (student_ra) REFERENCES Students(ra)
);
