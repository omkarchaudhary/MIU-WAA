package com.miu.lab3restapi.service;

import com.miu.lab3restapi.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
    Book getBook(String isbn);
    Books getAllBooks();
    Books searchBooks(String author);
}
