package com.qa.gorest.tests;


import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


import com.qa.gorest.client.RestClient;

import com.qa.gorest.pojo.User;

import com.qa.gorest.utils.StringUtils;

public class CreateUserTest {

	
	@Test
	public void createUserSheetTest() {
		User user = new User("prabesh", StringUtils.getRandomEmailId(), "male", "active");

		// post call
		// comment in s3
		 RestClient restClient= new RestClient();
		Integer user_id = restClient.post("/public/v2/users", "json", user, true, true).then().log().all().assertThat()
				.statusCode(201).and().extract().path("id");

		System.out.println("User Id :" + user_id);

		// get calling 
		// RestClient restClientGet= new RestClient(prop, baseURI);
		restClient.get("/public/v2/users/" + user_id, true, true).then().log().all().assertThat()
				.statusCode(200).and().body("id", equalTo(user_id));

	}

	// added in s3


	/*
	 * used in S1 ,comment in S2 RestClient restClient= new RestClient();
	 */
	
}
