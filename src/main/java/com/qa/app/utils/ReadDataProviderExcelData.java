package com.qa.app.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

//public class ReadDataProviderExcelData {
//	
//<
//	public static Object[][] getExcelDataIntoDataProvider(String excelPath,String sheetName) throws Exception
//	{
//		excelActions readExcelData = new excelActions();
//		int dataRowCount = readExcelData.getExcelRowCout(excelPath, sheetName);
//		System.out.println("Total row count "+dataRowCount);
//		int dataColumnCount = readExcelData.getExcelColumnCout(excelPath, sheetName);
//		System.out.println("Total column count "+dataColumnCount);
//		Object Data[][]= new Object[dataRowCount][dataColumnCount]; // pass my  count data in array
//
//		String StringCellvalue;
//		DataFormatter formatter= new DataFormatter();
//		for(int i=0;i<dataRowCount-1;i++)
//		{
//			for(int j=0;j<dataColumnCount;j++)
//			{
//				if(dataRowCount==-1)
//				     Data[i][j]= "";
//				     else 
//
//				    	 cellData = readExcelData.getCellData(excelPath, sheetName, i+1, j);
//				
//				StringCellvalue=formatter.formatCellValue(cellData);
//				    	 Data[i][j]=StringCellvalue;
//				System.out.println("Data is "+StringCellvalue);
//
//				
//			}
//		
//	}
//		return Data;
//	}
//	
//
//}
