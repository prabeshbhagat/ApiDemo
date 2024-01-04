package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAPITest extends  BaseTest{
	private String accessToken;

	
	@Parameters({"baseURI","grantType","clientId","clientSecret"})
	@BeforeMethod
	public void flightApiSetup(String baserURI,String grantType,String clientId,String clientSecret) {
		restClient= new RestClient(prop, baserURI);
		accessToken=restClient.getAccessToken(AMADEUS_ACCESSTOKEN_ENDPOINT, grantType, clientId, clientSecret);
	}
	
	@Test
	public void getFlighTInfoTest() {
		
		RestClient restClientFlight= new RestClient(prop, baseURI);
		
		Map<String,String> headersMap= new HashMap<String,String>();
		headersMap.put("Authorization", "Bearer "+accessToken);
		
		Map<String,Object> queryParamsMap= new HashMap<String,Object>();
		queryParamsMap.put("origin", "PAR");
		queryParamsMap.put("maxPrice", "200");
		
		Response flisghtDataresponse=restClientFlight.get(AMADEUS_FLIGHTAPI_ENDPOINT, headersMap, queryParamsMap, false, false)
				.then().log().all()
				.assertThat()
					.statusCode(APIHttpStatus.OK_200.getCode())
					.and()
					.extract().response();
		
		JsonPath js=flisghtDataresponse.jsonPath();
		  String type=js.get("data[0].type");
		  System.out.println("type"+type);
		  String origin=js.get("data[0].origin");
		  System.out.println("origin"+origin);
		

	}
	
	
	
	
	
	

}
