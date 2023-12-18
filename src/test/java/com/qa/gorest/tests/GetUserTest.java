package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetUserTest extends BaseTest  {
	
	
//	@BeforeMethod
//	public void setUp() {
//		restClient= new RestClient(prop, baseURI);
//	}
//	
	
	//url/public/v2/users?name?status
	@Test(enabled=true,priority=1)
	public void getAllUsersTest() {
		
		restClient.get("/public/v2/users",true, true)
				.then().log().all()
				.assertThat()
				.statusCode(200);	
	}
	
	//url/public/v2/users/1830615?name?status
	@Test(enabled=true,priority=2)
	public void getSingleUSerTest() {
		restClient.get("/public/v2/users/5371707",true, true)
				.then()
				.assertThat().log().all()
				.statusCode(200)
				.and()
				.body("id", equalTo(5371707));

	}
	
	//url?name?status
	@Test(enabled=true,priority=2)
	public void getUserWithQueryParamTest() {
		
		Map<String,Object> qMap= new HashMap<String,Object>();
		qMap.put("name", "Uttam");
		qMap.put("status", "active");
		
		restClient.get("/public/v2/users", null, qMap,true, true)
				.then()
				.assertThat().log().all()
				.statusCode(200);

		
	}
	
	
	

}
