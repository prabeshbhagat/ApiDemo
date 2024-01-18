package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetUserTest extends BaseTest  {
	
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}

	
	//url/public/v2/users?name?status
	@Test(enabled=true,priority=3)
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT,true, true)
				.then().log().all()
				.assertThat()
				.statusCode(APIHttpStatus.OK_200.getCode());	
	}
	
	//url/public/v2/users/1830615?name?status
	@Test(enabled=true,priority=2)
	public void getSingleUSerTest() {
		restClient.get(GOREST_ENDPOINT+"/5371707",true, true)
				.then()
				.assertThat().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode())
				.and()
				.body("id", equalTo(5371707));
	}
	
	
	
	@Test()
	public void Test() {
		System.out.println("test01");
		System.out.println("test02");
		System.out.println("test03");
		System.out.println("test04");
	}
	
	//url?name?status
	@Test(enabled=true,priority=1)
	public void getUserWithQueryParamTest() {	
		Map<String,Object> qMap= new HashMap<String,Object>();
		qMap.put("name", "Uttam");
		qMap.put("status", "active");	
		restClient.get(GOREST_ENDPOINT, null, qMap,true, true)
				.then()
				.assertThat().log().all()
				.statusCode(APIHttpStatus.OK_200.getCode());
	}

}
