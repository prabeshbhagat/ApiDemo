package com.qa.app.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationManager {
	
	//s2
	private Properties prop;
	private FileInputStream ip;
	
	public Properties initProp() {
		prop= new Properties();
		try {
			ip=new FileInputStream("./src/test/resources/conifg/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop; 
		
	}
	

	


}
