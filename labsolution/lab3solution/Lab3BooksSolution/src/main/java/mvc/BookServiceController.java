package mvc;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookServiceController {

	private Map<String, Book> books= new HashMap<String, Book>();
	
    public BookServiceController() {
    	books.put("123", new Book("123","Book 1", 20.95, "James Brown"));
    	books.put("124", new Book("124","Book 2", 20.95, "Mary Jones"));   	
	}

	@GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn = " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("/books")
    public ResponseEntity<?> addContact(@RequestBody Book book) {
    	books.put(book.getIsbn(), book);
        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
		books.put(isbn, book);
		return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> searchBooks(@RequestParam(value="author", required = false) String author) {
        Books allbooks = new Books();
        if (author == null){  //get all books
            allbooks.setBooks(books.values());
        }
        else{ //get books from an certain author
            String authorName = author.substring(1,author.length()-1); //remove quotes form the name
            List<Book> booklist = books.values().stream().filter(b->b.getAuthor().equals(authorName)).collect(Collectors.toList());
            allbooks.setBooks(booklist);
        }
        return new ResponseEntity<Books> (allbooks, HttpStatus.OK);
    }
}


