package com.library.models;

import jakarta.validation.constraints.*;

public class Book {
    private int book_id;

    @Pattern(regexp = "[A-Z]\\w*(\\s\\w+)*", message = "Big char first and no more 100 chars")
    private String bookTitle;

    @Pattern(regexp = "[A-Z]\\w*(\\s\\w+)*", message = "Big char first and no more 100 chars")
    private String bookAuthor;

    @Min(value = -5000, message = "No less than -5000")
    @Max(value = 2025, message = "No more than this year")
    private int bookYear;

    public Book() {}

    public Book(int book_id, String bookTitle, String bookAuthor, int bookYear) {
        this.book_id = book_id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
