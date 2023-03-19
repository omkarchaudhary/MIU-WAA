package com.miu.lab7graphqlbookapplication.controller;

import com.miu.lab7graphqlbookapplication.entity.Book;
import com.miu.lab7graphqlbookapplication.exception.CustomException;
import com.miu.lab7graphqlbookapplication.service.BookService;
import com.miu.lab7graphqlbookapplication.service.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @QueryMapping
    public Books getAllBooks(){
        return bookService.getAllBooks();
    }

    @MutationMapping
    public Book addBook(@Argument @Valid Book book){
        //checking to validate list of Object
        //for(Book book : books){
            bookService.addBook(book);
        //}
        return book;
    }

    @MutationMapping
    public Book updateBook(@Argument @Valid Book book) {
        bookService.updateBook(book);
        return book;
    }

    @MutationMapping
    public Book deleteBook(@Argument String isbn) {
        Book book = bookService.getBook(isbn);
        if (book != null) {
            bookService.deleteBook(isbn);
        }
        return book;
    }

    @QueryMapping
    public Book getBookByIsbn(@Argument String isbn) {
        return bookService.getBook(isbn);
    }

    @QueryMapping
    public Books searchBook(@Argument String author) {
        return bookService.searchBooks(author);

    }
}
