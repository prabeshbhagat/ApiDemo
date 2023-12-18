package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;


public class ReqResAPITest extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@Test
	public void ReqResgetAllUsersTest() {
		restClient.get("/api/users"+"?page=2",false, true)
				.then()
				.assertThat().log().all()
				.statusCode(200);	
		
		
	}
	

}
