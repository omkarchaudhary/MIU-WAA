package com.miu.lab3restapi.service.serviceImpl;

import com.miu.lab3restapi.entity.Book;
import com.miu.lab3restapi.service.BookService;
import com.miu.lab3restapi.service.Books;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    HashMap<String, Book> bookMap = new HashMap<>();

    @Override
    public void addBook(Book book) {
        if(book != null){
            bookMap.put(book.getIsbn(),book);
        }
    }

    @Override
    public void updateBook(Book book) {
        Optional<Book> existingBook = Optional.ofNullable(bookMap.get(book.getIsbn()));
        if(existingBook.isPresent()){
            bookMap.put(existingBook.get().getIsbn(),book);
        }
    }

    @Override
    public void deleteBook(String isbn) {
        bookMap.remove(isbn);
    }

    @Override
    public Book getBook(String isbn) {
        return bookMap.get(isbn);
    }

    @Override
    public Books getAllBooks() {
        Books bookList = new Books(bookMap.values());
        return bookList;
    }

    @Override
    public Books searchBooks(String author) {
        return searchBook(author);
    }

    public Books searchBook(String author) {
        List<Book> bookList = new ArrayList<>();
        for(Map.Entry<String, Book> entry: bookMap.entrySet()) {
            Book book = entry.getValue();
            if(author.equals(book.getAuthor()))
                bookList.add(book);
        }
        return new Books(bookList);
    }
}
