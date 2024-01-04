package com.qa.app.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.app.frameworkException.ApiFrameworkException;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private String getJsonResponseBodyAsString(Response response) {
		return response.getBody().asString();
	}
	
	
	public <T>T read(Response response,String jsonPath) {
		//String jsonResponse=response.getBody().asString();
		String jsonResponse=getJsonResponseBodyAsString(response);
			try {
				return JsonPath.read(jsonResponse,jsonPath );
			}catch(PathNotFoundException e) {
				e.printStackTrace();
				throw new ApiFrameworkException(jsonPath+"is not found..");
			}
	}
	
	public <T>List<T> readList(Response response,String jsonPath) {
		///String jsonResponse=response.getBody().asString();
		String jsonResponse=getJsonResponseBodyAsString(response);
		try {
			return JsonPath.read(jsonResponse,jsonPath );
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameworkException(jsonPath+"is not found..");
		}
}
	
	public <T>List<Map<String,T>> readListOfMaps(Response response,String jsonPath) {
		//String jsonResponse=response.getBody().asString();
		String jsonResponse=getJsonResponseBodyAsString(response);
		try {
			return JsonPath.read(jsonResponse,jsonPath );
		}catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameworkException(jsonPath+"is not found..");
		}
}

}
