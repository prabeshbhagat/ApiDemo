package com.qa.gorest.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.test.utils.JsonPathValidatorTest;

import io.restassured.response.Response;

public class circuitTest extends BaseTest {

	@BeforeMethod
	public void setUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllCircuitTest() {
		restClient.get(CIRCUIT_ENDPOINT+"/2019/circuits.json", false, true).then().assertThat().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test
	public void getCountryCircuitTest() {
		Response circuitResponse=restClient.get(CIRCUIT_ENDPOINT+"/2019/circuits.json", false, true);
		JsonPathValidatorTest js=new JsonPathValidatorTest();
		List<String> countryList=js.readList(circuitResponse, "$..Circuits..country");
		System.out.println(countryList);
		Assert.assertTrue(countryList.contains("Bahrain"));
				
	}
	
	@Test
	public void getLocalityLongitudeCircuitTest() {
		Response circuitResponse=restClient.get(CIRCUIT_ENDPOINT+"/2019/circuits.json", false, true);
		JsonPathValidatorTest js=new JsonPathValidatorTest();
		List<Map<String,Object>> countryList=js.readMapList(circuitResponse, "$..Circuits[*].[\"circuitId\",\"circuitName\"]");
		System.out.println(countryList);
			
	}



}
