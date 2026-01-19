package com.booker.api.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * REST Utilities
 * Wrapper methods for HTTP operations with Allure logging
 */
public class RestUtils {

    private RestUtils() {
    }

    @Step("POST request to: {endpoint}")
    public static Response post(String endpoint, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET request to: {endpoint}")
    public static Response get(String endpoint) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET request to: {endpoint} with path params")
    public static Response get(String endpoint, Map<String, ?> pathParams) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("PUT request to: {endpoint}")
    public static Response put(String endpoint, Map<String, ?> pathParams, Object body) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("DELETE request to: {endpoint}")
    public static Response delete(String endpoint, Map<String, ?> pathParams) {
        return given()
                .spec(RequestSpecFactory.getRequestSpec())
                .log().all()
                .pathParams(pathParams)
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
