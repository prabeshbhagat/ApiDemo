package com.qa.petstore.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.app.pojo.petStore_user;
import com.qa.gorest.base.BaseTest;

import io.restassured.response.Response;

public class Create_update_delete_petStoreUser extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] UsersData() {
		return new Object[][] { { "ShaliniUN", "Shalinifn", "Sharmaln", "pwd123", "mobile","0" },
			 { "ShettaUN", "Shettafn", "Shettaln", "pwd123", "mobile","0" },
			 { "RahulUN", "Rahulfn", "Rahulln", "pwd123", "mobile","0" }
			
		};
	}

	@Test(dataProvider = "UsersData")
	public void create_user(String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus)
	{
		petStore_user create_user_pojo = new petStore_user(username, firstName, lastName, email, password, phone, userStatus);
		restClient.post(PETSTORE_USER_ENDPOINT, "json", create_user_pojo, false, true)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	
	}
}
