package com.qa.gorest.utils;

public class StringUtils {
	
	
	public static String getRandomEmailId() {
		return "apiAuto"+System.currentTimeMillis()+"@apimail.com";
		//return "apiAuto"+UUID.randomUUID()+"mail.com";
	}

}
