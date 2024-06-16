package repositories.interfaces;

import entities.Loan;
import entities.enums.LoanStatus;

import java.util.ArrayList;

public interface ILoansRepository {
    public void create(Loan loan);
    public ArrayList<Loan> findAll();
    public ArrayList<Loan> findByStatus(LoanStatus status);
    public Loan findByBookIsbnAndStudentRa(int isbn, int ra);
    public ArrayList<Loan> findNonFinishedsByBookIsbn(int isbn);
    public void update(Loan loan);
    public void batchUpdate(ArrayList<Loan> loans);
}
