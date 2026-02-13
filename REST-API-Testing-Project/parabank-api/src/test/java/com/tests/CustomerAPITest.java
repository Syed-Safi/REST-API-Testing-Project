package com.tests;

import com.base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerAPITest extends BaseTest {

    private static String userId;

    // -------------------------
    // 1️⃣ Create Customer
    // -------------------------
    @Test(priority = 1)
    public void createCustomer() {
        String requestBody = "{ \"name\": \"John\", \"username\": \"jdoe\", \"email\": \"john@example.com\" }";

        Response response = requestSpec
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().all()
                .extract().response();

        userId = response.jsonPath().getString("id");
        System.out.println("Created User ID: " + userId);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "John");
    }

    // -------------------------
    // 2️⃣ Get Customer
    // -------------------------
    @Test(priority = 2)
    public void getCustomer() {
        String existingUserId = "1"; // Predefined user in JSONPlaceholder

        Response response = requestSpec
                .when()
                .get("/users/" + existingUserId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("id"), existingUserId);
    }


    // -------------------------
    // 3️⃣ Update Customer
    // -------------------------
    @Test(priority = 3)
    public void updateCustomer() {
        String existingUserId = "1"; // Predefined user on JSONPlaceholder

        String requestBody = "{ \"name\": \"John Updated\", \"username\": \"jdoe2\", \"email\": \"john2@example.com\" }";

        Response response = requestSpec
                .body(requestBody)
                .when()
                .put("/users/" + existingUserId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "John Updated");
    }


    // -------------------------
    // 4️⃣ Delete Customer
    // -------------------------
    @Test(priority = 4)
    public void deleteCustomer() {
        Response response = requestSpec
                .when()
                .delete("/users/" + userId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200); // JSONPlaceholder returns 200 on delete
    }
}
