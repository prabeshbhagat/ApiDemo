package com.qa.app.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qa.app.utils.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class PetStore_user {

	@JsonProperty("id")
	private String id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("userStatus")
	private String userStatus;


	
	public PetStore_user(String username, String firstName, String lastName, String emailid,
			String password, String phone, String userStatus) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.email = email;
		this.email = StringUtils.getRandomEmailId();
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
		
		System.out.println("Data ser are : "+username+">"+firstName+">"+lastName+">"+password+">"+phone+">"+userStatus);
		
	}
	
	

}
	
	
	


