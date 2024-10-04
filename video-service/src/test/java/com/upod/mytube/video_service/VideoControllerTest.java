package com.upod.mytube.video_service;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class VideoControllerTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    @Order(1)
    void testVideoYukle() {
      given()
        .log().ifValidationFails()
        .multiPart("yukleyiciId", 1L)
        .multiPart("kanalId", 2L)
        .multiPart("baslik", "Test Video")
        .multiPart("aciklama", "Test Aciklama")
        .multiPart("etiketler", Set.of("test", "video"))
        .multiPart("dosya", "testfile.mp4", "test content".getBytes())
    .when()
        .post("/videolar/yukle")
    .then()
        .log().ifValidationFails()
        .statusCode(200)
        .body("videoId", is(1));
    }


    @Test
    @Order(2)
    void testVideoGetirFound() throws Exception {
        given()
            .log().ifValidationFails()
        .when()
            .get("/videolar/{videoId}", 1L)
        .then()
            .log().ifValidationFails()
            .statusCode(HttpStatus.OK.value())
            .body("videoId", equalTo(1))
            .body("baslik.deger", equalTo("Test Video"))
            .body("aciklama", equalTo("Test Aciklama"));
    }

}
