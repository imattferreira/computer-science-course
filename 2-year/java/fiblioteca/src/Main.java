import entities.*;
import entities.enums.*;
import repositories.implementations.*;
import repositories.in_memory.*;
import repositories.interfaces.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  private Scanner scanner;
  private IStudentsRepository studentsRepository;
  private IBooksRepository booksRepository;
  private ILoansRepository loansRepository;

  public Main() {
      this.scanner = new Scanner(System.in);
//      this.studentsRepository = new InMemoryStudentsRepository();
//      this.booksRepository = new InMemoryBooksRepository();
//      this.loansRepository = new InMemoryLoansRepository();

      this.studentsRepository = new StudentsRepository();
      this.booksRepository = new BooksRepository();
      this.loansRepository = new LoansRepository();
  }

  public static void main(String[] args) {
    new Main().init();
  }

  public void init() {
    this.showOptionsTable();

    int option = this.scanner.nextInt();

    switch (option) {
      case 0:
        this.exit();
        break;
      case 1:
        this.createStudent();
        break;
      case 2:
        this.showStudents();
        break;
      case 3:
        this.createBook();
        break;
      case 4:
        this.showBooks();
        break;
      case 5:
        this.createLoan();
        break;
      case 6:
        this.showLoans();
        break;
      case 7:
        this.finishLoan();
        break;
      case 8:
        this.listLateLoans();
        break;
      case 9:
        this.updatePendentLoans();
        break;
      default:
        System.out.println("Parece que você digitou uma opção inválida, tente novamente.");
        break;
      }
  }

  private void showOptionsTable() {
      System.out.println("=======================");
      System.out.println("Selecione uma opção");
      System.out.println("0 - Exit");
      System.out.println("1 - Criar aluno");
      System.out.println("2 - Listar alunos");
      System.out.println("3 - Criar Livro");
      System.out.println("4 - Listar livros");
      System.out.println("5 - Criar empréstimos");
      System.out.println("6 - Listar empréstimos");
      System.out.println("7 - Finalizar empréstimo");
      System.out.println("8 - Listar empréstimos não finalizados");
      System.out.println("9 - Atualizar empréstimos não atrasados");
      System.out.println("=======================");
  }

  private void exit() {
    System.exit(0);
  }

  private void createStudent() {
    System.out.println("Digite o RA do estudante:");
    int ra = this.scanner.nextInt();

    Student existingStudent = this.studentsRepository.findByRa(ra);

    if (existingStudent != null) {
      System.out.println("Estudante já existe.");
       return;
    }

    System.out.println("Digite o Nome do estudante:");
    String name = this.scanner.next();

    Student student = new Student(ra, name);

    this.studentsRepository.create(student);
  }

  private void showStudents() {
    ArrayList<Student> students = this.studentsRepository.findAll();

    for (Student student : students) {
      student.log();
    }
  }

  private void createBook() {
    System.out.println("Digite o ISBN do livro:");
    int ra = this.scanner.nextInt();

    Book existingBook = this.booksRepository.findByIsbn(ra);

    if (existingBook != null) {
      System.out.println("Livro já existe.");
      return;
    }

    System.out.println("Digite o Título do livro:");
    String title = this.scanner.next();

    System.out.println("Digite o Autor do livro:");
    String author = this.scanner.next();

    Book book = new Book(ra, title, author);

    this.booksRepository.create(book);
  }

  private void showBooks() {
    ArrayList<Book> books = this.booksRepository.findAll();

    for (Book book : books) {
      book.log();
    }
  }

  private void showLoans() {
    ArrayList<Loan> loans = this.loansRepository.findAll();

    for (Loan loan : loans) {
      loan.log();
    }
  }

  private void createLoan() {
      System.out.println("Digite o RA do estudante:");
      int ra = this.scanner.nextInt();

      Student existingStudent = this.studentsRepository.findByRa(ra);

      if (existingStudent == null) {
        System.out.println("Estudante não encontrado.");
        return;
      }

      System.out.println("Digite o ISBN do livro:");
      int isbn = this.scanner.nextInt();

      Book existingBook = this.booksRepository.findByIsbn(isbn);

      if (existingStudent == null) {
        System.out.println("Livro não encontrado.");
        return;
      }

      boolean bookAlreadyInActiveLoan = this.loansRepository.findNonFinishedsByBookIsbn(isbn).size() > 0;

      if (bookAlreadyInActiveLoan) {
        System.out.println("Livro já está em um empréstimo ativo");
        return;
      }

      Loan newLoan = new Loan(existingBook, existingStudent);

      this.loansRepository.create(newLoan);
  }

  private void finishLoan() {
      System.out.println("Digite o RA do estudante:");
      int ra = this.scanner.nextInt();
      System.out.println("Digite o ISBN do livro:");
      int isbn = this.scanner.nextInt();

      Loan loan = this.loansRepository.findByBookIsbnAndStudentRa(isbn, ra);

      if (loan == null) {
        System.out.println("Empréstimo não encontrado!");
      }

      LocalDate now = LocalDate.now();
      boolean isLateLoan = loan.getLoanExpectedFinishDate().isBefore(now);

      if (isLateLoan) {
          System.out.println("Empréstimo atrasado!");
      }

      loan.setStatus(LoanStatus.FINISHED);

      this.loansRepository.update(loan);

      System.out.println("Empréstimo finalizado com sucesso!");
  }

  private void listLateLoans() {
    ArrayList<Loan> lateLoans = this.loansRepository.findByStatus(LoanStatus.LATE);

    for (Loan loan : lateLoans) {
      loan.log();
    }
  }

  private void updatePendentLoans() {
    ArrayList<Loan> loans = this.loansRepository.findAll();
    ArrayList<Loan> updatedLoans = new ArrayList<Loan>();
    LocalDate now = LocalDate.now();

    for (Loan loan : loans) {
        if (loan.getStatus() != LoanStatus.FINISHED && loan.getLoanExpectedFinishDate().isBefore(now)) {
            loan.setStatus(LoanStatus.LATE);
        }

        updatedLoans.add(loan);
    }

    this.loansRepository.batchUpdate(updatedLoans);
  }
}