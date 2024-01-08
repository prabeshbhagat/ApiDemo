package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
import com.qa.app.client.RestClient;
import com.qa.app.pojo.PetStore_user;
import com.qa.app.utils.ExcelUtils;

import com.qa.app.utils.StringUtils;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.ApiConstants;

 
public class test extends BaseTest{
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] getUsersSheetData() {
		return ExcelUtils.getTestData(ApiConstants.PETSTORE_USER_SHEET_NAME);
	}
	
	/*
	@DataProvider(name="UsersData")
	public Object[][] UsersData() {
		 Object currentData[][] = new Object[][] {
				 ReadDataProviderExcelData.getExcelDataIntoDataProvider("./src/test/resources/testData/pet_store_user_data.xlsx", "Sheet1")
		 		};
		return currentData;
	}
	*/
	//need to pass as object type parameter to avoid lot of parameter words
	@Test(dataProvider = "getUsersSheetData")
	public void create_petStore_user(String username,String firstName,String lastName,String password,String phone,String userStatus)
	{
		
		System.out.println("Inside create petstore");
		PetStore_user create_user_pojo_ob = new PetStore_user( username, firstName, lastName,StringUtils.getRandomEmailId(), password, phone, userStatus);
		
		restClient.post(PETSTORE_USER_ENDPOINT, "json", create_user_pojo_ob, false, true)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	
	}
}