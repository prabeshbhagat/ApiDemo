package com.qa.petstore.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.app.client.RestClient;
import com.qa.app.pojo.PetStore_user;
import com.qa.app.utils.StringUtils;
import com.qa.gorest.base.BaseTest;


public class Create_update_delete_petStoreUser extends BaseTest {
	
		
	@BeforeMethod
	public void setUp() {
		restClient= new RestClient(prop, baseURI);
	}
	
	@DataProvider(name="UsersData")
	public Object[][] UsersData() {
		 Object currentData[][] = new Object[][] { { "ShettaUN", "Shettafn", "Shettaln", "pwd123", "mobile","0" },
			 { "ShettaUN", "Shettafn", "Shettaln", "pwd123", "mobile","0" },
			 { "RahulUN", "Rahulfn", "Rahulln", "pwd123", "mobile","0" }
			
		};
		return currentData;
	}
	
	

	@Test(dataProvider = "UsersData")
	//need to pass as object type parameter to avoid lot of parameter words
	public void create_petStore_user(String username,String firstName,String lastName,String password,String phone,String userStatus)
	{
		PetStore_user create_user_pojo = new PetStore_user( username, firstName, lastName,StringUtils.getRandomEmailId(), password, phone, userStatus);
		
		//System.out.println("Data is "+create_user_pojo);
		restClient.post(PETSTORE_USER_ENDPOINT, "json", create_user_pojo, false, true)
		.then().log().all()
		.assertThat()
		.statusCode(200);
	
	}
	
	
}
