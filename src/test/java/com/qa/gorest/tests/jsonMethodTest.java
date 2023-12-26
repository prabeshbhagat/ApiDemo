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
		//ResponseBody responseBody = response.getBody();
		//String jsonResponseString = responseBody.asString();
		String responseAsString = response.asString();
		
		//Get Id data
		List<String> idList = JsonPath.read(responseAsString, "$.[*].id");
		System.out.println("List of Ids : "+idList);
		
		//Get Name Data
		List<String> nameList = JsonPath.read(responseAsString, "$.[*].name");
		System.out.println("List of Names : "+nameList);

<<<<<<< HEAD
//		JsonPath jResPath = new JsonPath(jsonResponseString);
//		String id = jResPath.getString("id");
//		//System.out.println(id);
//		
//		String category_id = jResPath.getString("category.id");
//		//System.out.println(category_id);
//		
//		String category_name = jResPath.getString("category.name");
		//System.out.println(category_name);
=======
		//Get Category ids	
		List<String> CategoryIdList = JsonPath.read(responseAsString, "$.[*].category.id");
		//System.out.println(CategoryIdList);
		
		//Get category Name
		List<String> CategoryNameList = JsonPath.read(responseAsString, "$.[*].category.name");
		//System.out.println(CategoryNameList);
		
>>>>>>> 5476132f02a4e38a27aff431ebdc8b48d1ce5e88
		
		System.out.println("Count of collected records => "+idList.size());
		
			
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
		for(int i=0;i<nameList.size();i++)
			for(int j=i+1;j<nameList.size();j++)
			{
				
				if(nameList.get(i) == (nameList.get(j)))
				{
					System.out.println("Duplicate elemnts are : "+nameList.get(j));
				}
				else
				{
					//System.out.println("No Dupicate element found.");
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
		//System.out.println(CategoryNames);
		for(String e:CategoryNames) {
			{
				//System.out.println(e);
			}
		}
		//System.out.println(CategoryNames);
		
		//Get category Name
		//jason path Working Example=> $.[*].category.id,name
		List<Map<String,Object>> CategoryList = JsonPath.read(ResponseInString, "$.[*].category[\"id\",\"name\"]");
		System.out.println(CategoryList);
		System.out.println("=========================================");
		for(Map e1:CategoryList) {
			
			Object idValue= e1.get("id");
			String nameValue=(String) e1.get("name");
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("idValue is "+idValue);
			System.out.println("nameValue is "+nameValue);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
		
	}
	}
		
}
