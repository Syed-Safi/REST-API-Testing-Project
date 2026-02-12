package com.tests;

import com.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerAPITest extends BaseTest {

	@Test
	public void getCustomerDetails() {

		Response response = RestAssured.given().when().get("/parabank/services/bank/customers/");

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 400);
	}

	@Test
	public void createCustomer() {
		String requestBody = "<customer>" + "<firstName>John</firstName>" + "<lastName>David</lastName>"
				+ "</customer>";
		Response response = RestAssured.given().header("Content-Type", "application/xml").body(requestBody).when()
				.post("/parabank/services/bank/customers");
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 201);

	}


}
