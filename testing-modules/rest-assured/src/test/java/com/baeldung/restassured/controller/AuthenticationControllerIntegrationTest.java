package com.baeldung.restassured.controller;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import javax.annotation.PostConstruct;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.authentication.FormAuthConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerIntegrationTest {

    private static final String USER = "user";
    private static final String PASSWORD = "pass";
    private static final String EXPECTED_SECURED_RESOURCE = "secured-resource";

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @Test
    public void givenNoAuthentication_whenRequestSecuredResource_thenUnauthorizedResponse() {

        get(uri + "/authentication/secured").then()
            .assertThat()
            .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void givenBasicAuthentication_whenRequestSecuredResource_thenResourceRetrieved() {

        given().auth()
            .basic(USER, PASSWORD)
            .when()
            .get(uri + "/authentication/secured")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body(Matchers.is(EXPECTED_SECURED_RESOURCE));
    }

    @Test
    public void givenFormAuthentication_whenRequestSecuredResource_thenResourceRetrieved() {

        given().auth()
            .form(USER, PASSWORD, FormAuthConfig.springSecurity())//???????
            .when()
            .get(uri + "/authentication/secured")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body(Matchers.is(EXPECTED_SECURED_RESOURCE + "asd"));
    }
}
