package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.app.pojo.PetStore_user;
import com.qa.app.pojo.User;

import com.qa.app.utils.ExcelUtils;
import com.qa.app.utils.StringUtils;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.ApiConstants;

public class PetstoreApiTest extends BaseTest {
	
	//Object currentData[][];
	Object [][] currentData;
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	
	@DataProvider
	public Object[][] getUsersData() {
		return currentData = new Object[][] { 
			{ "ShaliniUN", "Shalinifn", "Sharmaln", "pwd123", "mobile","0" },
			 { "ShettaUN", "Shettafn", "Shettaln", "pwd123", "mobile","0" },
			 { "RahulUN", "Rahulfn", "Rahulln", "pwd123", "mobile","0" }
			
		};
		
	}
	
	@DataProvider
	public Object[][] getUsersSheetData() {
		return ExcelUtils.getTestData(ApiConstants.GOREST_USER_SHEET_NAME);
	}
	
	
	@Test(dataProvider="getUsersData" ,enabled=true)
	public void createUserSheetTest(String un,String fn,String ln,String pwd,String mob,String status) {
		// actual line :
		PetStore_user puser = new PetStore_user(un,fn,ln, StringUtils.getRandomEmailId(), pwd, mob,status);
		
	
		// post call
		// comment in s3
		
		 int code=restClient.post(PETSTORE_USER_ENDPOINT, "json", puser, false, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.OK_200.getCode())
				.and().extract().path("code");

		System.out.println("code :" + code);

//		// get calling 
		RestClient restClientGet= new RestClient(prop, baseURI);
		restClientGet.get(PETSTORE_USER_ENDPOINT+"/"+un, false, true).then().log().all().assertThat()
			.statusCode(APIHttpStatus.OK_200.getCode()).and().body("username", equalTo(un));

	}
	

	
	
	@Test(enabled=false)
	public void getSingleUSerTest() {
		

		// post call
		// comment in s3
		
		 restClient.get(PETSTORE_USER_ENDPOINT+"/ShaliniUN", false, true)
		 			.then().log().all()
		 			.assertThat()
		 			.statusCode(APIHttpStatus.OK_200.getCode())
		 			.and()
		 			.body("username", equalTo("ShaliniUN"));
				
	// get calling 
//		// RestClient restClientGet= new RestClient(prop, baseURI);
//		restClient.get("/public/v2/users/" + user_id, true, true).then().log().all().assertThat()
//				.statusCode(APIHttpStatus.OK_200.getCode()).and().body("id", equalTo(user_id));

	}
	

}
