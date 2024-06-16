package repositories.in_memory;

import entities.Book;
import entities.Loan;
import entities.Student;
import entities.enums.LoanStatus;
import library.Parser;
import repositories.interfaces.ILoansRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class InMemoryLoansRepository implements ILoansRepository {
    private ArrayList<Loan> repository;
    private final Repository manipulator;
    private static InMemoryLoansRepository _instance;

    public InMemoryLoansRepository() {
        this.loadData();
        this.manipulator = new Repository("loans.csv");
    }

    public static InMemoryLoansRepository getInstance() {
        if (_instance == null) {
            _instance = new InMemoryLoansRepository();
        }

        return _instance;
    }

    private void loadData() {
      this.repository = new ArrayList<Loan>();

      ArrayList<String[]> data = this.manipulator.getContent();

      for (String[] line : data) {
        this.repository.add(this.mapper(line));
      }
    }

    private Loan mapper(String[] fields) {
      Student student = new Student(0, ""); // TODO
      Book book = new Book(0, "", ""); // TODO
      int id = Parser.parseInt(fields[2]);
      LoanStatus status = LoanStatus.valueOf(fields[3]);
      LocalDate loanStartDate = Parser.parseLocalDate(fields[4]);
      LocalDate loanExpectedFinishDate = Parser.parseLocalDate(fields[5]);

      return new Loan(id, book, student, status, loanStartDate, loanExpectedFinishDate);
    }

    @Override
    public void create(Loan loan) {
      this.repository.add(loan);
    }

    @Override
    public ArrayList<Loan> findAll() {
        return this.repository;
    }

    @Override
    public ArrayList<Loan> findByStatus(LoanStatus status) {
      ArrayList<Loan> result = new ArrayList<Loan>();

      for (Loan loan : this.repository) {
        if (loan.getStatus() == status) {
          result.add(loan);
        }
      }

      return result;
    }

    @Override
    public Loan findByBookIsbnAndStudentRa(int isbn, int ra) {
      Loan result = null;

      for (Loan loan : this.repository) {
        if (loan.getBook().getIsbn() == isbn && loan.getStudent().getRa() == ra) {
          result = loan;
          break;
        }
      }

      return result;
    }

    @Override
    public ArrayList<Loan> findNonFinishedsByBookIsbn(int isbn) {
      ArrayList<Loan> result = new ArrayList<Loan>();

      for (Loan loan : this.repository) {
          if (loan.getBook().getIsbn() == isbn && loan.getStatus() != LoanStatus.FINISHED) {
            result.add(loan);
          }
      }

      return result;
    }

    @Override
    public void update(Loan loan) {
      for (int i = 0; i <= this.repository.size(); i++) {
        Loan current = this.repository.get(i);

        boolean isSameBookIsbn = current.getBook().getIsbn() == loan.getBook().getIsbn();
        boolean isSameStudentRa = current.getStudent().getRa() == loan.getStudent().getRa();

        if (isSameBookIsbn && isSameStudentRa) {
          this.repository.add(i, loan);
        }
      }
    }

    @Override
    public void batchUpdate(ArrayList<Loan> loans) {
      this.repository = loans;
    }
}
