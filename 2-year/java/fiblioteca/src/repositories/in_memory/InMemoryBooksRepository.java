package repositories.in_memory;

import entities.Book;
import library.Parser;
import repositories.interfaces.IBooksRepository;

import java.util.ArrayList;

public class InMemoryBooksRepository implements IBooksRepository {
    private ArrayList<Book> repository;
    private final Repository manipulator;
    private static InMemoryBooksRepository _instance;

    public InMemoryBooksRepository() {
        this.loadData();
        this.manipulator = new Repository("books.csv");
    }

    public static InMemoryBooksRepository getInstance() {
        if (_instance == null) {
            _instance = new InMemoryBooksRepository();
        }

        return _instance;
    }

    private void loadData() {
      this.repository = new ArrayList<Book>();

      ArrayList<String[]> data = this.manipulator.getContent();

      for (String[] line : data) {
        this.repository.add(this.mapper(line));
      }
    }

    private Book mapper(String[] fields) {
      int isbn = Parser.parseInt(fields[0]);
      String title = Parser.parseStr(fields[1]);
      String author = Parser.parseStr(fields[2]);

      return new Book(isbn, title, author);
    }

    @Override
    public void create(Book book) {
        this.repository.add(book);
    }

    @Override
    public ArrayList<Book> findAll() {
        return this.repository;
    }

    @Override
    public Book findByIsbn(int isbn) {
      Book result = null;

      for (Book book : this.repository) {
        if (book.getIsbn() == isbn) {
          result = book;
          break;
        }
      }

      return result;
    }
}
