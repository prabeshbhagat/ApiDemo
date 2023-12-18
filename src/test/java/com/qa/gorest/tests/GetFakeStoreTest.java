package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;


public class GetFakeStoreTest extends BaseTest {
	
//	@BeforeMethod
//	public void setUp() {
//		restClient= new RestClient(prop, baseURI);
//	}
	
	@Test
	public void GetFakeStoreProductsTest() {	
		restClient.get("/products", false, true)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
	
	@Test
	public void GetFakeStoreProductsCategoriesTest() {	
		restClient.get("/products"+"/categories", false, true)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}

}
