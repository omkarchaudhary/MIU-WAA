package com.miu.lab7graphqlbookapplication.service.serviceImpl;

import com.miu.lab7graphqlbookapplication.entity.Book;
import com.miu.lab7graphqlbookapplication.repository.BookRepository;
import com.miu.lab7graphqlbookapplication.service.BookService;
import com.miu.lab7graphqlbookapplication.service.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

    @Override
    public Book getBook(String isbn) {
        return bookRepository.getBook(isbn);
    }

    @Override
    public Books getAllBooks() {
        return  bookRepository.getAllBooks();
    }

    @Override
    public Books searchBooks(String author) {
        return bookRepository.searchBooks(author);
    }

}
