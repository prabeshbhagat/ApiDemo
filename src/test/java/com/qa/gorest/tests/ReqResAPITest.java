package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;




public class ReqResAPITest extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@Test
	public void ReqResgetAllUsersTest() {
		restClient.get(REQRES_ENDPOINT+"?page=2",false, true)
				.then()
				.assertThat().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());	
		
		
	}
	

}
