package repositories.implementations;

import entities.Book;
import repositories.interfaces.IBooksRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BooksRepository implements IBooksRepository {
    private Repository repository;

    public BooksRepository() {
        this.repository = Repository.getInstance();
    }

    @Override
    public void create(Book book) {
        try {
            PreparedStatement statement = this.repository.getConnection()
                .prepareStatement(
                    "INSERT INTO Books ("
                        + "isbn,"
                        + "title, "
                        + "author "
                        + ") VALUES (?,?,?);"
                );

            statement.setInt(0, book.getIsbn());
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());

            statement.execute();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> result = new ArrayList<Book>();

        try {
            ResultSet lines = this.repository
                .getConnection()
                .prepareStatement("SELECT * FROM Books")
                .executeQuery();

            while (lines.next()) {
                int isbn = lines.getInt("isbn");
                String title = lines.getString("title");
                String author = lines.getString("author");

                Book book = new Book(isbn, title, author);

                result.add(book);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public Book findByIsbn(int isbn) {
        try {
            ResultSet lines = this.repository
                .getConnection()
                .prepareStatement("SELECT * FROM Books WHERE isbn = " + isbn)
                .executeQuery();

            lines.next();

            String title = lines.getString("title");
            String author = lines.getString("author");

            return new Book(isbn, title, author);
        } catch (SQLException err) {
            err.printStackTrace();
        } finally {
            return null;
        }
    }
}
