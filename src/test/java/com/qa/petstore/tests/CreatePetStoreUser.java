package com.qa.petstore.tests;

import java.io.File;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreatePetStoreUser {

	
	/*
	 * API details 	: https://petstore.swagger.io/v2/user
	 * POST 		: Create user 
	 * Scenario 1	: Create a User
	 * end point	: /user
	 * Response Body: {
  						"code": 200,
  						"type": "unknown",
  						"message": "9223372036854775807"
}
	*/
	
	String user_id=null;
	
	@Test()
	public void CreatePetStoreUser()
	{
		File jsonFile = new File("./src/test/resources/TestData/Create_user.json");
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/user");
		//requestSpec.header("Authorization","Bearer 4dab37282bbf0bdf8c1826704826d1ba669a407710b6fb732d449283b86b045a");
		requestSpec.contentType(ContentType.JSON);
		//below code if you want to pass json file data to body payload
		requestSpec.body(jsonFile);
				
				
		Response response = requestSpec.post();
				
		//System.out.println("Response Status Code is : "+response.statusLine());
		//System.out.println("Response Body is : "+response.body().asPrettyString());
		//Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"Record Created.");
				
		Assert.assertEquals(response.statusCode(), 200,"New User Created.");
			
		JsonPath jsonPathEvaluator = response.jsonPath();
		int resCode = jsonPathEvaluator.get("code");
		user_id = jsonPathEvaluator.get("message");
		//System.out.println("Request retuned response Code : "+resCode);
		//System.out.println("Created User ID is : "+user_id);
		
	}
	
	@Test(dependsOnMethods = "CreatePetStoreUser")
	public void getUserDetail()
	{
		//Gievn data for "username": "TestPetStoreUser"
		RequestSpecification rspec = RestAssured.given();
		rspec.baseUri("https://petstore.swagger.io")
		.basePath("/v2/user/"+"TestPetStoreUser")
		.contentType(ContentType.JSON);
		
		Response response = rspec.get();
		System.out.println("Response status code is "+response.statusCode());
		System.out.println("Created user details : "+response.body().asPrettyString());
		
	}
	
	@Test(dependsOnMethods = "getUserDetail")
	public void UpdateuserDetails()
	{
		File jsonFileToUpdateUser = new File("./src/test/resources/TestData/Update_user.json");
		RequestSpecification requestSpec = RestAssured.given()
				.baseUri("https://petstore.swagger.io")
				.basePath("/v2/user/TestPetStoreUser")
				.contentType(ContentType.JSON);
		Response response = requestSpec.body(jsonFileToUpdateUser).put();
		System.out.println("Response Code After put : "+response.getStatusCode());
		System.out.println("Response body After put : "+response.body().asPrettyString());
		
	}
	
	@Test(dependsOnMethods = "UpdateuserDetails")
	public void DeletePetStoreUser()
	{
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/user/TestPetStoreUser_updated");
		requestSpec.contentType(ContentType.JSON);
		Response response = requestSpec.delete();
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"Record Deleted.");
		Assert.assertEquals(response.statusCode(), 200,"Record Deleted.");
		System.out.println("Response Body After record delete "+response.body().asPrettyString());
		
	}
}
