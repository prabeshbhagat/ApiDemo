package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configurations.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	protected ConfigurationManager config;
	protected RestClient restClient ;
	protected Properties prop;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baserURI) {
		
		RestAssured.filters(new AllureRestAssured());//allure report
		
		config= new ConfigurationManager();
		prop=config.initProp();
		this.baseURI=baserURI;
		restClient= new RestClient(prop, baserURI);	
		System.out.println(baseURI);
	}
	
	
}
