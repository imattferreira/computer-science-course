package entities;

public class Book {
    private int isbn;
    private String title;
    private String author;

    public Book(int isbn, String title, String author) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setAuthor(author);
    }

    public int getIsbn() {
        return this.isbn;
    }

    private void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    public void log() {
        System.out.println("=======================");
        System.out.println("ISBN - " + this.getIsbn());
        System.out.println("Título - " + this.getTitle());
        System.out.println("Autor - " + this.getAuthor());
        System.out.println("=======================");
    }
}
