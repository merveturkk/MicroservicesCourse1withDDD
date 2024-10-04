package com.upod.mytube.subscription_service.controller;

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
public class AbonelikControllerTest {


    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void testAboneOl() {
      given()
        .log().ifValidationFails()
        .param("kanalId", 1L)
        .param("kullaniciId", 1L)
    .when()
        .post("/abonelikler/abone-ol")
    .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("abonelikId", is(1));
    }

    @Test
    @Order(2)
    public void testKullaniciAboneliklerim() {
      given()
        .log().ifValidationFails()
        .param("kullaniciId", 1L)
    .when()
        .get("/abonelikler/kullanici/aboneliklerim")
    .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("[0].abonelikId", is(1));
    }

    @Test
    @Order(3)
    public void testAbonelikIptal() {
      given()
        .log().ifValidationFails()
        .param("kanalId", 1L)
        .param("kullaniciId", 1L)
    .when()
        .post("/abonelikler/abonelik-iptal")
    .then()
        .log().ifValidationFails()
        .statusCode(200);
    }

}
