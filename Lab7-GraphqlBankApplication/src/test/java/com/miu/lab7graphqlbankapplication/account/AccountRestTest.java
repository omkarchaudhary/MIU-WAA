package com.miu.lab7graphqlbankapplication.account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class AccountRestTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testCreateAccount(){
        // add the account
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("accountHolder", "Omkar");
                }})
                .when()
                .post("/account")
                .then()
                .statusCode(200);
        // get the account and verify
        given()
                .when()
                .get("account/12345")
                .then()
                .statusCode(200)
                .and()
                .body("accountNumber",equalTo(12345))
                .body("accountHolder",equalTo("Omkar"))
                .body("balance",equalTo(0.0f))
                .body("transactionList",equalTo(new ArrayList<>()));
        //cleanup
        given()
                .when()
                .delete("account/12345");
    }

    @Test
    public void testDepositAmount(){
        // add the account
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("accountHolder", "Omkar");
                }})
                .when()
                .post("/account")
                .then()
                .statusCode(200);
        //deposit amount
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("amount", 1000.0f);
                }})
                .when()
                .post("/account/deposit")
                .then()
                .statusCode(200);
        // get the account and verify
        given()
                .when()
                .get("account/12345")
                .then()
                .statusCode(200)
                .and()
                .body("accountNumber",equalTo(12345))
                .body("accountHolder",equalTo("Omkar"))
                .body("balance",equalTo(1000.0f))
                .body("transactionList",hasSize(1));
        //cleanup
        given()
                .when()
                .delete("account/12345");
    }

    @Test
    public void testWithdrawAmount(){
        // add the account
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("accountHolder", "Omkar");
                }})
                .when()
                .post("/account")
                .then()
                .statusCode(200);
        //deposit amount
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("amount", 1000.0f);
                }})
                .when()
                .post("/account/deposit")
                .then()
                .statusCode(200);

        //withdraw amount
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("amount", 500.0f);
                }})
                .when()
                .post("/account/withdraw")
                .then()
                .statusCode(200);
        // get the account and verify
        given()
                .when()
                .get("account/12345")
                .then()
                .statusCode(200)
                .and()
                .body("accountNumber",equalTo(12345))
                .body("accountHolder",equalTo("Omkar"))
                .body("balance",equalTo(500.0f))
                .body("transactionList",hasSize(2));
        //cleanup
        given()
                .when()
                .delete("account/12345");
    }

    @Test
    public void testGetAccount(){
        // add the account
        given()
                .contentType(ContentType.JSON)
                //.body(account)
                .queryParams(new HashMap<>() {{
                    put("accountNumber", 12345);
                    put("accountHolder", "Omkar");
                }})
                .when()
                .post("/account")
                .then()
                .statusCode(200);

        // get the account and verify
        given()
                .when()
                .get("account/12345")
                .then()
                .statusCode(200)
                .and()
                .body("accountNumber",equalTo(12345))
                .body("accountHolder",equalTo("Omkar"))
                .body("balance",equalTo(0.0f))
                .body("transactionList",hasSize(0));
        //cleanup
        given()
                .when()
                .delete("account/12345");
    }
}
