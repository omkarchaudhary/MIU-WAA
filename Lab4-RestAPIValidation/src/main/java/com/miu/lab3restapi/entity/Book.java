package com.miu.lab3restapi.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    @NotNull
    @Size(min = 5,max = 10)
    private String isbn;
    @NotNull
    @Size(min = 5,max = 15)
    private String author;
    @NotNull
    @Size(min = 5,max = 15)
    private String title;

    @NotNull
    private double price;

    public Book() {
    }

    public Book(String isbn, String author, String title, double price) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
