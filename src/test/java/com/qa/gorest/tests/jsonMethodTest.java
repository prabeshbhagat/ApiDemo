package com.qa.gorest.tests;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.i18n.phonenumbers.AsYouTypeFormatter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;
import com.jayway.jsonpath.JsonPath;

public class jsonMethodTest {
	
	@Test()
	public void readJsonData()
	{
		
		//Get Available pets
		RequestSpecification reSpec = RestAssured.given()
				.baseUri("https://petstore.swagger.io")
				.basePath("v2/pet/findByStatus")
				.param("status", "available")
				.accept(ContentType.JSON);
		
		
		/*
		Response resBody = reSpec.get();
		//To print available data
		System.out.println(resBody.asPrettyString());
	*/	
		//Get Sold pets
		/*
		RequestSpecification reSpec = RestAssured.given()
				.baseUri("https://petstore.swagger.io")
				.basePath("v2/pet/findByStatus")
				.param("status", "sold")
				.accept(ContentType.JSON);
		*/
		
		
		Response response = reSpec.get();
		ResponseBody responseBody = response.getBody();
		String jsonResponseString = responseBody.asString();

//		JsonPath jResPath = new JsonPath(jsonResponseString);
//		String id = jResPath.getString("id");
//		//System.out.println(id);
//		
//		String category_id = jResPath.getString("category.id");
//		//System.out.println(category_id);
//		
//		String category_name = jResPath.getString("category.name");
		//System.out.println(category_name);
		
		List<Long> idList = response.jsonPath().getList("id");
		//System.out.println(idList);
		System.out.println("Count of collected records => "+idList.size());
		
		List<String> CategoryNameList = response.jsonPath().getList("category.name");
		System.out.println(CategoryNameList);
		
		if(CategoryNameList.contains("Jerry"))
		{
			System.out.println("Item found.");
		}
		else
		{
			System.out.println("Item not found.");
		}
		
		//here duplicate elements will remove after list to hashset,
		//as Hashset never allow duplicate values
		HashSet<String> CategoryAsSet = new HashSet<>(CategoryNameList);
		if(CategoryAsSet.contains("Jerry"))
		{
			System.out.println("Item found.");
		}
		else
		{
			System.out.println("Item not found.");
		}
		
		//Find duplicate names
		for(int i=0;i<idList.size();i++)
			for(int j=i+1;j<idList.size();j++)
			{
				
				if(idList.get(i).equals((Number) idList.get(j).longValue()))
				{
					System.out.println("Duplicate elemnts are : "+idList.get(j));
				}
				else
				{
					System.out.println("No Dupicate element found.");
				}
			}
		
	}

	@Test
	public void getPetStoreData()
	{
		RequestSpecification reSpec = RestAssured.given()
				.baseUri("https://petstore.swagger.io")
				.basePath("v2/pet/findByStatus")
				.param("status", "available")
				.accept(ContentType.JSON);
		
		Response response = reSpec.get();
		String ResponseInString = response.asString();
		//System.out.println(ResponseInString);
		
		List<String> CategoryNames = JsonPath.read(ResponseInString, "$[*].category.name");
		System.out.println(CategoryNames);
		
		
		
		
	}
}
