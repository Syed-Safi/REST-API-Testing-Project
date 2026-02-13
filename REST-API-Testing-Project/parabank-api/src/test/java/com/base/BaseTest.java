package com.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        // Base URI for JSONPlaceholder
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Reusable RequestSpecification with logging
        requestSpec = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}
