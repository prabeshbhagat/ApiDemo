package com.qa.test.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkException.ApiFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidatorTest {
	
	public String getJsonResposneAsString(Response response) {
		return response.getBody().asString();
	}


	public <T> T read(Response response,String jsonPath) {
		String jsonResposne=getJsonResposneAsString(response);
		try {
			return JsonPath.read(jsonResposne, jsonPath);		
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameworkException(jsonPath+" is not found..");
		}
	}
	
	public <T> List<T> readList(Response response,String jsonPath) {
		String jsonResposne=getJsonResposneAsString(response);
		try {
			return JsonPath.read(jsonResposne, jsonPath);		
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameworkException(jsonPath+" is not found..");
		}
	}
	
	public <T> List<Map<String,T>> readMapList(Response response,String jsonPath) {
		String jsonResposne=getJsonResposneAsString(response);
		try {
			return JsonPath.read(jsonResposne, jsonPath);		
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameworkException(jsonPath+" is not found..");
		}
	}

}
