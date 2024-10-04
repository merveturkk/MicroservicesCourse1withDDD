package com.upod.mytube.userservice.controller;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class KullaniciControllerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testKayitOl() {
      given()
        .log().ifValidationFails()
        .contentType(ContentType.JSON)
        .body("""
                {
                    "email" : "emre@upod.dev",
                    "sifre" : "C0KG1ZL1S1FRE"
                }
                """)
        .when()
        .post("/kullanicilar/kayit")
        .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("kullaniciId", is(1));
    }

    @Test
    public void testH2Console() {
      given()
        .log().ifValidationFails()
        .contentType(ContentType.JSON)
        .body("""
                {
                    "email" : "emre@upod.dev",
                    "sifre" : "C0KG1ZL1S1FRE"
                }
                """)
        .when()
        .get("/h2-console")
        .then()
        .log().ifValidationFails()
        .statusCode(200);
    }

}