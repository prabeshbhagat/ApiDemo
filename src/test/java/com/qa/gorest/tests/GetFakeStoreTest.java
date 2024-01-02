package com.qa.gorest.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.test.utils.JsonPathValidator;

import io.restassured.response.Response;


public class GetFakeStoreTest extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@Test
	public void GetFakeStoreProductsTest() {	
		restClient.get(FAKESTORE_ENDPOINT, false, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test
	public void GetFakeStoreProductsCategoriesTest() {	
		restClient.get(FAKESTORE_ENDPOINT+"/categories", false, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test
	public void GetFakeStorePriceCategoriesTest() {	
		Response jsonResponse=restClient.get(FAKESTORE_ENDPOINT+"/categories", false, true);
		JsonPathValidator js=new JsonPathValidator();				
		List<Map<String,Object>> priceCategory=js.readListOfMaps(jsonResponse, "$[?(@.price<=50)][\"price\",\"category\"]");
		System.out.println(priceCategory);
	}

}
