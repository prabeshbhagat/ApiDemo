package com.qa.gorest.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import com.qa.app.client.RestClient;
import com.qa.app.pojo.User;
import com.qa.app.utils.ExcelUtils;
import com.qa.app.utils.StringUtils;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.ApiConstants;

public class CreateUserTest extends BaseTest {
	
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	
	@DataProvider
	public Object[][] getUsersData() {
		return new Object[][] { { "Shalini", "female", "active" },
								{ "Shyam", "male", "active" },
								{ "Supria", "female", "inactive" } };
	}
	
//	@DataProvider
//	public Object[][] getUsersSheetData() {
//
//		return ExcelUtils.getTestData(".src/test/resources/TestData/ApiTestData.xlsx",ApiConstants.GOREST_USER_SHEET_NAME);
//	}
	
	
	@Test(dataProvider="getUsersData")
	public void createUserSheetTest(String name,String gender,String status) {
		User user = new User(name, StringUtils.getRandomEmailId(), gender, status);

		// post call
		// comment in s3
		
		Integer user_id = restClient.post(GOREST_ENDPOINT, "json", user, true, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.CREATED_201.getCode()).and().extract().path("id");

		System.out.println("User Id :" + user_id);

		// get calling 
		// RestClient restClientGet= new RestClient(prop, baseURI);
//		restClient.get("/public/v2/users/" + user_id, true, true).then().log().all().assertThat()
//				.statusCode(APIHttpStatus.OK_200.getCode()).and().body("id", equalTo(user_id));

	}

	// added in s3


	/*
	 * used in S1 ,comment in S2 RestClient restClient= new RestClient();
	 */
	
}
