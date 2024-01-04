package com.qa.test.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class petStore_user {

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


	public petStore_user(String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.email = email;
		this.userStatus = userStatus;
		
	}

}
	
	
	


