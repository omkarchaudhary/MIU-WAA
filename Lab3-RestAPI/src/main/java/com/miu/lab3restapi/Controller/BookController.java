package com.miu.lab3restapi.Controller;

import com.miu.lab3restapi.entity.Book;
import com.miu.lab3restapi.exception.CustomException;
import com.miu.lab3restapi.service.BookService;
import com.miu.lab3restapi.service.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllbooks(){
        Books book = bookService.getAllBooks();
        if (book == null) {
            return new ResponseEntity<CustomException>(new CustomException("No book available."),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Books>(book,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return  new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteContact(@PathVariable String isbn) {
        Book book = bookService.getBook(isbn);
        if (book == null) {
            return new ResponseEntity<CustomException>(new CustomException("Book with isbn = " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBook(isbn);
        if (book == null) {
            return new ResponseEntity<CustomException>(new CustomException("Book with isbn = " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/search/{author}")
    public ResponseEntity<?> searchBook(@PathVariable String author) {
        Books book = bookService.searchBooks(author);
        if (book == null) {
            return new ResponseEntity<CustomException>(new CustomException("Book with author = " + author + " is not available"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
