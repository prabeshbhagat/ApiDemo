package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.ApiConstants;
import com.qa.test.pojo.petStore_user;
import com.qa.test.pojo.User;
import com.qa.test.utils.ExcelUtils;
import com.qa.test.utils.StringUtils;

public class PetstoreApiTest extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	
	@DataProvider
	public Object[][] getUsersData() {
		return new Object[][] { { "ShaliniUN", "Shalinifn", "Sharmaln", "pwd123", "mobile","0" },
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
		petStore_user puser = new petStore_user(un,fn,ln, StringUtils.getRandomEmailId(), pwd, mob,status);

		// post call
		// comment in s3
		
		 int code=restClient.post(PETSTORE_ENDPOINT, "json", puser, false, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.OK_200.getCode())
				.and().extract().path("code");

		System.out.println("code :" + code);

//		// get calling 
		RestClient restClientGet= new RestClient(prop, baseURI);
		restClientGet.get(PETSTORE_ENDPOINT+"/"+un, false, true).then().log().all().assertThat()
			.statusCode(APIHttpStatus.OK_200.getCode()).and().body("username", equalTo(un));

	}
	
	
	@Test(enabled=false)
	public void getSingleUSerTest() {
		

		// post call
		// comment in s3
		
		 restClient.get(PETSTORE_ENDPOINT+"/ShaliniUN", false, true)
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
