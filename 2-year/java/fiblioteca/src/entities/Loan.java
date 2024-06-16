package entities;

import entities.enums.LoanStatus;

import java.time.LocalDate;

public class Loan {
    private int id;
    private Book book;
    private Student student;
    private LoanStatus status;
    private LocalDate loanStartDate;
    private LocalDate loanExpectedFinishDate;

    public Loan(Book book, Student student) {
      this.setBook(book);
      this.setStudent(student);
      this.setStatus(LoanStatus.ACTIVE);
      this.setLoanStartDate(LocalDate.now());
      this.setLoanExpectedFinishDate(LocalDate.now().plusWeeks(2));
    }

    public Loan(
      int id,
      Book book,
      Student student,
      LoanStatus status,
      LocalDate loanStartDate,
      LocalDate loanExpectedFinishDate
    ) {
      this.setId(id);
      this.setBook(book);
      this.setStudent(student);
      this.setStatus(status);
      this.setLoanStartDate(loanStartDate);
      this.setLoanExpectedFinishDate(loanExpectedFinishDate);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return this.student;
    }

    private void setStudent(Student student) {
        this.student = student;
    }

    public LoanStatus getStatus() { return this.status; }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getLoanStartDate() {
        return this.loanStartDate;
    }

    private void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getLoanExpectedFinishDate() {
        return this.loanExpectedFinishDate;
    }

    private void setLoanExpectedFinishDate(LocalDate loanExpectedFinishDate) {
        this.loanExpectedFinishDate = loanExpectedFinishDate;
    }

    public void log() {
      System.out.println("=======================");
      System.out.println("Estudante RA - " + this.getStudent().getRa());
      System.out.println("Livro ISBN - " + this.getBook().getIsbn());
      System.out.println("Status - " + this.getStatus());
      System.out.println("Início do empréstimo - " + this.getLoanStartDate().toString());
      System.out.println("Fim do empréstimo esperado - " + this.getLoanExpectedFinishDate().toString());
      System.out.println("=======================");
    }
}
