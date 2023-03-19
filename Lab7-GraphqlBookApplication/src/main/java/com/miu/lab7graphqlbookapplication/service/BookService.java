package com.miu.lab7graphqlbookapplication.service;

import com.miu.lab7graphqlbookapplication.entity.Book;

public interface BookService {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
    Book getBook(String isbn);
    Books getAllBooks();
    Books searchBooks(String author);
}
