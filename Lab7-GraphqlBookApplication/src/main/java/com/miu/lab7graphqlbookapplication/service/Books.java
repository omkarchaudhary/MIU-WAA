package com.miu.lab7graphqlbookapplication.service;

import com.miu.lab7graphqlbookapplication.entity.Book;

import java.util.Collection;
import java.util.List;

public class Books {
    private Collection<Book> books;

    public Books(Collection<Book> books) {
        this.books  = books;
    }
    public Collection<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
