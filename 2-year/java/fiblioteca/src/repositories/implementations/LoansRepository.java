package repositories.implementations;

import entities.Book;
import entities.Loan;
import entities.Student;
import entities.enums.LoanStatus;
import library.Parser;
import repositories.interfaces.ILoansRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoansRepository implements ILoansRepository {
    private Repository repository;

    public LoansRepository() {
        this.repository = Repository.getInstance();
    }

    @Override
    public void create(Loan loan) {
        try {
             PreparedStatement statement = this.repository.getConnection()
                .prepareStatement(
                    "INSERT INTO Loans ("
                            + "book_isbn,"
                            + "student_ra, "
                            + "status, "
                            + "start_date, "
                            + "expected_finish_date" +
                        ") VALUES (?,?,?,?,?);"
                );

             statement.setInt(0, loan.getBook().getIsbn());
            statement.setInt(1, loan.getStudent().getRa());
            statement.setString(2, loan.getStatus().toString());
            statement.setDate(3, Parser.parseDate(loan.getLoanStartDate()));
            statement.setDate(4, Parser.parseDate(loan.getLoanExpectedFinishDate()));

            statement.execute();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public ArrayList<Loan> findAll() {
        ArrayList<Loan> result = new ArrayList<Loan>();

        try {
            ResultSet lines = this.repository
                .getConnection()
                .prepareStatement(
                    "SELECT "
                           + "Loans.id,"
                           + "Loans.book_isbn,"
                           + "Loans.student_ra,"
                           + "Loans.status,"
                           + "Loans.start_date,"
                           + "Loans.expected_finish_date,"
                           + "Books.title AS book_title,"
                           + "Books.author AS book_author,"
                           + "Students.name AS student_name"
                           + "FROM `Loans`"
                        + "JOIN Books ON Loans.book_isbn = Books.isbn"
                        + "JOIN Students ON Loans.student_ra = Students.ra"
                        + "ORDER BY Loans.start_date ASC;"
                )
                .executeQuery();

            while (lines.next()) {
                int id = lines.getInt("id");
                int studentRa = lines.getInt("student_ra");
                int isbn = lines.getInt("isbn");
                LoanStatus status = lines.getObject("status", LoanStatus.class);
                LocalDate loanStartDate = Parser.parseLocalDate(lines.getDate("start_date"));
                LocalDate loanExpectedFinishDate = Parser.parseLocalDate(lines.getDate("expected_finish_date"));
                String title = lines.getString("book_title");
                String author = lines.getString("book_author");
                String studentName = lines.getString("student_name");

                Book book = new Book(isbn, title, author);
                Student student = new Student(studentRa, studentName);
                Loan loan = new Loan(id, book, student, status, loanStartDate, loanExpectedFinishDate);

                result.add(loan);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public ArrayList<Loan> findByStatus(LoanStatus status) {
        ArrayList<Loan> result = new ArrayList<Loan>();

        try {
            ResultSet lines = this.repository.getConnection().prepareStatement(
                "SELECT "
                        + "Loans.id,"
                        + "Loans.book_isbn,"
                        + "Loans.student_ra,"
                        + "Loans.status,"
                        + "Loans.start_date,"
                        + "Loans.expected_finish_date,"
                        + "Books.title AS book_title,"
                        + "Books.author AS book_author,"
                        + "Students.name AS student_name"
                    + "FROM `Loans`"
                    + "JOIN Books ON Loans.book_isbn = Books.isbn"
                    + "JOIN Students ON Loans.student_ra = Students.ra"
                    + "WHERE status != `FINISHED` AND status = " + status
                    + "ORDER BY Loans.start_date ASC;"
            ).executeQuery();

            while (lines.next()) {
                int id = lines.getInt("id");
                int studentRa = lines.getInt("studentRa");
                int isbn = lines.getInt("isbn");
                Date loanStartDate = lines.getDate("start_date");
                Date loanExpectedFinishDate = lines.getDate("expected_finish_date");
                String title = lines.getString("title");
                String author = lines.getString("author");
                String studentName = lines.getString("name");

                Book book = new Book(isbn, title, author);
                Student student = new Student(studentRa, studentName);
                Loan loan = new Loan(
                    id,
                    book,
                    student,
                    status,
                    Parser.parseLocalDate(loanStartDate),
                    Parser.parseLocalDate(loanExpectedFinishDate)
                );

                result.add(loan);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public Loan findByBookIsbnAndStudentRa(int isbn, int ra) {
        Loan result = null;

        try {
            ResultSet lines = this.repository.getConnection().prepareStatement(
                "SELECT "
                        + "Loans.id,"
                        + "Loans.book_isbn,"
                        + "Loans.student_ra,"
                        + "Loans.status,"
                        + "Loans.start_date,"
                        + "Loans.expected_finish_date,"
                        + "Books.title AS book_title,"
                        + "Books.author AS book_author,"
                        + "Students.name AS student_name"
                    + "FROM `Loans`"
                    + "JOIN Books ON Loans.book_isbn = Books.isbn"
                    + "JOIN Students ON Loans.student_ra = Students.ra"
                    + "WHERE status != `FINISHED` AND book_isbn = " + isbn
                    + "ORDER BY Loans.start_date ASC "
                    + "LIMIT 1;"
            ).executeQuery();

            while (lines.next()) {
                int id = lines.getInt("id");
                int studentRa = lines.getInt("studentRa");
                LoanStatus status = lines.getObject("status", LoanStatus.class);
                Date loanStartDate = lines.getDate("start_date");
                Date loanExpectedFinishDate = lines.getDate("expected_finish_date");
                String title = lines.getString("title");
                String author = lines.getString("author");
                String studentName = lines.getString("name");

                Book book = new Book(isbn, title, author);
                Student student = new Student(studentRa, studentName);
                Loan loan = new Loan(
                    id,
                    book,
                    student,
                    status,
                    Parser.parseLocalDate(loanStartDate),
                    Parser.parseLocalDate(loanExpectedFinishDate)
                );

                result = loan;
                break;
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public ArrayList<Loan> findNonFinishedsByBookIsbn(int isbn) {
        ArrayList<Loan> result = new ArrayList<Loan>();

        try {
            ResultSet lines = this.repository.getConnection().prepareStatement(
                "SELECT "
                    + "Loans.id,"
                    + "Loans.book_isbn,"
                    + "Loans.student_ra,"
                    + "Loans.status,"
                    + "Loans.start_date,"
                    + "Loans.expected_finish_date,"
                    + "Books.title AS book_title,"
                    + "Books.author AS book_author,"
                    + "Students.name AS student_name"
                    + "FROM `Loans`"
                + "JOIN Books ON Loans.book_isbn = Books.isbn"
                + "JOIN Students ON Loans.student_ra = Students.ra"
                + "WHERE status != `FINISHED` AND book_isbn = " + isbn
                + "ORDER BY Loans.start_date ASC;"
            ).executeQuery();

            while (lines.next()) {
                int id = lines.getInt("id");
                int studentRa = lines.getInt("studentRa");
                LoanStatus status = lines.getObject("status", LoanStatus.class);
                Date loanStartDate = lines.getDate("start_date");
                Date loanExpectedFinishDate = lines.getDate("expected_finish_date");
                String title = lines.getString("title");
                String author = lines.getString("author");
                String studentName = lines.getString("name");

                Book book = new Book(isbn, title, author);
                Student student = new Student(studentRa, studentName);
                Loan loan = new Loan(
                    id,
                    book,
                    student,
                    status,
                    Parser.parseLocalDate(loanStartDate),
                    Parser.parseLocalDate(loanExpectedFinishDate)
                );

                result.add(loan);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public void update(Loan loan) {
        try {
            this.repository.getConnection()
                .createStatement()
                .executeUpdate(
                    "UPDATE Loans"
                        + " WHERE id = " + loan.getId()
                        + "SET "
                        + "book_isbn = " + loan.getBook().getIsbn() + ", "
                        + "student_ra = " + loan.getStudent().getRa() + ", "
                        + "status = " + loan.getStatus() + ", "
                        + "start_date = " + loan.getLoanStartDate() + ", "
                        + "expected_finish_date = " + loan.getLoanExpectedFinishDate() + ";"
                );
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void batchUpdate(ArrayList<Loan> loans) {
        // TODO
    }
}
