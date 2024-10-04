package com.upod.mytube.comment_service.controller;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class YorumControllerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void testYorumEkle() {
      given()
        .log().ifValidationFails()
        .param("videoId", 1L)
        .param("yazarKullaniciId", 1L)
        .param("metin", "Test Video")
    .when()
        .post("/yorumlar/ekle")
    .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("yorumId", is(1));
    }

    @Test
    @Order(2)
    public void testYorumGetir() {
      given()
        .log().ifValidationFails()
        .param("videoId", 1L)
        .param("yazarKullaniciId", 1L)
        .param("metin", "Test Video")
    .when()
        .get("/yorumlar/video/{videoId}", 1L)
    .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("[0].yorumId", is(1));
    }


}