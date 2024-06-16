package repositories.interfaces;

import entities.Book;

import java.util.ArrayList;

public interface IBooksRepository {
    public void create(Book book);
    public ArrayList<Book> findAll();
    public Book findByIsbn(int isbn);
}
