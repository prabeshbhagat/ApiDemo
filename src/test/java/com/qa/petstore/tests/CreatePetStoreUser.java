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
	 * API details : https://petstore.swagger.io/v2/user
	 * POST : Create user 
	 */
	
	@Test
	public void CreatePetStoreUser()
	{
		RequestSpecification reqSpec = RestAssured.given();
				
				
		File jsonFile = new File("./src/test/java/testData/Create_user.json");

		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/user");
		//requestSpec.header("Authorization","Bearer 4dab37282bbf0bdf8c1826704826d1ba669a407710b6fb732d449283b86b045a");
		requestSpec.contentType(ContentType.JSON);
						
		//below code if you want to pass json file data to body payload
		requestSpec.body(jsonFile);
				
				
		Response response = requestSpec.post();
				
		System.out.println("Response Status Code is : "+response.statusLine());
		System.out.println("Response Body is : "+response.body().asPrettyString());
		//Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"Record Created.");
				
		Assert.assertEquals(response.statusCode(), 200,"New User Created.");
			
		JsonPath jsonPathEvaluator = response.jsonPath();
		int resCode = jsonPathEvaluator.get("code");
		String resmessage = jsonPathEvaluator.get("message");
		System.out.println("Request retuned response Code : "+resCode);
		System.out.println("Created User ID is : "+resmessage);
		
	}
	
	@Test(dependsOnMethods = "CreatePetStoreUser")
	public void DeletePetStoreUser()
	{
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://petstore.swagger.io");
		requestSpec.basePath("/v2/user/TestPetStoreUser");
		requestSpec.contentType(ContentType.JSON);
		Response response = requestSpec.delete();
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"Record Deleted.");
		Assert.assertEquals(response.statusCode(), 200,"Record Deleted.");
	}
}
