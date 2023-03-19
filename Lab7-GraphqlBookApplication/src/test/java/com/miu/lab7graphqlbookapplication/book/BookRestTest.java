package com.miu.lab7graphqlbookapplication.book;

import com.miu.lab7graphqlbookapplication.entity.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class BookRestTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testAddBook(){
        // add the book
        Book book = new Book("12345", "Gayal", "Cracking Interview", 320.0f);
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/book")
                .then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("book/12345")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("12345"))
                .body("author",equalTo("Gayal"))
                .body("title",equalTo("Cracking Interview"))
                .body("price",equalTo(320.0f));
        //cleanup
        given()
                .when()
                .delete("book/12345");
    }

    @Test
    public void testUpdateBook() {
        // add the book
        Book book = new Book("12345", "Gayal", "Cracking Interview", 320.0f);
        Book updateBook = new Book("12345", "Gayal", "Java Cracking Interview", 320.0f);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/book").then()
                .statusCode(200);
        //update book
        given()
                .contentType("application/json")
                .body(updateBook)
                .when().put("/book").then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("book/12345")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("12345"))
                .body("author",equalTo("Gayal"))
                .body("title",equalTo("Java Cracking Interview"))
                .body("price",equalTo(320.0f));
        //cleanup
        given()
                .when()
                .delete("book/Bob");
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("12345", "Gayal", "Cracking Interview", 320.0f);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/book").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("book/12345")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("12345"))
                .body("author",equalTo("Gayal"))
                .body("title",equalTo("Cracking Interview"))
                .body("price",equalTo(320.0f));
        //cleanup
        given()
                .when()
                .delete("book/Mary");
    }

    @Test
    public void testDeleteBook() {
        // add the book to be deleted book
        Book book = new Book("12345", "Gayal", "Cracking Interview", 320.0f);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/book").then()
                .statusCode(200);

        given()
                .when()
                .delete("book/12345");

        given()
                .when()
                .get("book/12345")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("Book with isbn = 12345 is not available"));
    }

    @Test
    public void testGetAllBooks() {
        // add the books
        Book book = new Book("12345", "Gayal", "Cracking Interview", 320.0f);
        Book secondBook = new Book("12346", "ABC", "Java Cracking Interview", 300.0f);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/book").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(secondBook)
                .when().post("/book").then()
                .statusCode(200);

        // get all books and verify
        given()
                .when()
                .get("book")
                .then()
                .statusCode(200)
                .and()
                .body("books.isbn", hasItems("12345", "12346"))
                .body("books.author",hasItems("Gayal", "ABC"))
                .body("books.title",hasItems("Cracking Interview", "Java Cracking Interview"))
                .body("books.price",hasItems(320.0f, 300.0f));
        //cleanup
        given()
                .when()
                .delete("book/12345");
        given()
                .when()
                .delete("book/12346");
    }
}
