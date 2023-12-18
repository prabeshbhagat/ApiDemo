package com.qa.gorest.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;




public class circuitTest extends BaseTest {

	@BeforeMethod
	public void setUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllCircuitTest() {
		restClient.get( "/api/f1/2019/circuits.xml", false, true).then().assertThat().log().all()
				.statusCode(200);
	}



}
