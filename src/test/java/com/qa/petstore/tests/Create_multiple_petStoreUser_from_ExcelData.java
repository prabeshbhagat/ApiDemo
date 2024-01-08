package com.qa.petstore.tests;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.app.pojo.PetStore_user;
import com.qa.app.utils.ExcelUtils;
import com.qa.app.utils.ReadDataProviderExcelData;
import com.qa.app.utils.StringUtils;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.ApiConstants;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Create_multiple_petStoreUser_from_ExcelData extends BaseTest{
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	/*
	@DataProvider
	public Object[][] UsersData() {
		 Object currentData[][] = new Object[][] { 
			 { "ShaliniUN", "Shalinifn", "Sharmaln", "pwd123", "mobile","0" },
			 { "ShettaUN", "Shettafn", "Shettaln", "pwd123", "mobile","0" },
			 { "RahulUN", "Rahulfn", "Rahulln", "pwd123", "mobile","0" }
			
		};
		return currentData;
	}
	*/
	//below dataprovider is from Prabesh & working fine to use uncomment
	
	@DataProvider
	public Object[][] UsersData() {
		return ExcelUtils.getTestData("./src/test/resources/testData/pet_store_user_data.xlsx","Sheet1");
	}
	
	
	
	@Test(dataProvider = "UsersData")
	public void create_petStore_user(String username,String firstName,String lastName,String password,String phone,String userStatus)
	{
		
		//Create pet store user by pulling excel test data
		PetStore_user create_user_pojo_ob = new PetStore_user(username, firstName, lastName, StringUtils.getRandomEmailId(), password, phone, "0");
		
		Response response = restClient.post(PETSTORE_USER_ENDPOINT, "json", create_user_pojo_ob, false, true);
		response.then().log().all()
		.assertThat()
		.statusCode(200);
		
		//below is for record created message, varrie from application to application
		//Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_CREATED,"Record Created.");
		Assert.assertEquals(response.statusCode(), 200,"New User Created.");
	
		// Retrieve the body of the Response
		ResponseBody resBody = response.getBody();
		String resBodyAsString = resBody.asString();
		System.out.println("Response body is "+resBodyAsString);
		//Validate response body has attribute
		Assert.assertEquals(resBodyAsString.contains("code"), true);
		//Get Attribute value
		JsonPath jsonPathEvaluator = response.jsonPath();
		Integer code = jsonPathEvaluator.get("code");
		// Validate the response attribute value
		Assert.assertEquals(code, 200, "Correct code received in the Response");
		
	}

		
}
