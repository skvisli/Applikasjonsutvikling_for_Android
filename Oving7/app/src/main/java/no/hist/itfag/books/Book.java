package no.hist.itfag.books;

import java.util.ArrayList;

public class Book {
    private String bookAuthor;
    private String bookTitle;

    public Book(String autor, String title) {
        bookAuthor = autor;
        bookTitle = title;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
