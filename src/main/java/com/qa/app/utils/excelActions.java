package com.qa.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelActions {

	/*
	 * Method Name 	: getExcelRowCout
	 * Arguments	: File Path,Sheet Name
	 * Return		: Get total row count in integer for the given Excel sheet
	 */
	public static int getExcelRowCout(String filePath,String sheetName)
	{
		int rowCount = 0;
		File file = new File(filePath);
		if(!file.isFile() && !file.exists())
		{
			System.out.println("Invalid file or path.");
			
		}
		else {
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		}
			 catch (IOException e) {
				e.printStackTrace();
			}
		
		//Get total number of rows in the sheet
		rowCount = sheet.getLastRowNum()+1;
		}
		return rowCount;
		
	}
	
	/*
	 * Method Name 	: getExcelColumnCout
	 * Arguments	: File Path,Sheet Name
	 * Return		: Get total column count in integer for the given Excel sheet
	 */
	public static int getExcelColumnCout(String filePath,String sheetName)
	{
		int columnCount = 0;
		File file = new File(filePath);
		if(!file.isFile() && !file.exists())
		{
			System.out.println("Invalid file or path.");
			
		}
		else 
		{
		FileInputStream fis;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		}
			 catch (IOException e) {
				e.printStackTrace();
			}
		
		//Get total number of columns in the sheet
		columnCount = sheet.getRow(0).getLastCellNum();
		
		}
		return columnCount;
	}
	
	/*
	 * Method Name 	: getCellData
	 * Arguments	: File Path,Sheet Name,Cell row index number,Cell column index number
	 * Return		: Get cell data in string for the given Excel sheet
	 * Description	: To get a cell data from Excel sheet
	 */
	public static Cell getCellData(String filePath, String sheetName, int rowNumber, int columnNumber)
	{
		Cell cellData=null;
		File file = new File(filePath);
		if(!file.isFile() && !file.exists())
		{
			System.out.println("Invalid file or path.");
			
		}
		else
		{
			FileInputStream fis;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet = null;
			
			try {
				//Create an object of FileInputStream class to read excel file
				fis = new FileInputStream(file);
				//Create object of XSSFWorkbook class
				workbook = new XSSFWorkbook(fis);
				//Read excel sheet by sheet name
				sheet = workbook.getSheet(sheetName);
				//Get data from specified cell
				//Get cell
				
				cellData = sheet.getRow(rowNumber).getCell(columnNumber);
				//System.out.println("Cell Data in String is => "+cellData);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return cellData;
	}
	
	
	/*
	 * Method Name 	: getColumnNumberForTheRowData
	 * Arguments	: File Path,Sheet Name,row index number,row data
	 * Return		: Column number in integer
	 * Description	: To Get the column number for particular row data
	 */
	public static int getColumnNumberForTheRowData(String filePath,String sheetName,int rowNumber,String cellData)
	{
		int columnNumber=0;
		Boolean dataExist = false;
		File file = new File(filePath);
		if(!file.isFile() && !file.exists())
		{
			System.out.println("Invalid file or path.");
			
		}
		else
		{
			FileInputStream fis;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet = null;
									
			try {
				//Create an object of FileInputStream class to read excel file
				fis = new FileInputStream(file);
				//Create object of XSSFWorkbook class
				workbook = new XSSFWorkbook(fis);
				//Read excel sheet by sheet name
				sheet = workbook.getSheet(sheetName);
				Row row = sheet.getRow(rowNumber-1);
				for(int i =0;i<row.getPhysicalNumberOfCells();i++)
				{
					String getCellData = sheet.getRow(rowNumber-1).getCell(i).toString();
					//System.out.println("Cell data now is "+getCellData);
					if(getCellData.equalsIgnoreCase(cellData))
					{
						columnNumber=i+1;
						//System.out.println("Data "+cellData+" exist in "+columnNumber+" column for "+rowNumber+" row.");
						dataExist=true;
						break;
					}
					else
					{
						//System.out.println("Data "+cellData+" is not found for "+rowNumber+" row.");
						dataExist=false;
						columnNumber=-1;
					}
				}
				if(!dataExist)
				{
					System.out.println("Data "+cellData+" is not found for "+rowNumber+" row.");
				}
				
		
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		 
		}
		return columnNumber;
	}
	
	
	/*
	 * Method Name 	: updateCellData
	 * Arguments	: File Path,Sheet Name,Cell row index number,Cell column index number,String dataToWrite
	 * Return		: No return(void) only appropriate message log
	 * Description	: To update the cell data in existing excel sheet
	 */
	public static void writeCellData(String filePath, String fileName, String sheetName,int rowNumber,int columnNumber,String dataToWrite)
	{
		//File file = new File(filePath);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		FileOutputStream OutputStream = null;
		String filePathWithExtension = null;
		
			try
			{
				
				// create blank workbook
		        workbook = new XSSFWorkbook();
		        // Create a blank sheet
		        sheet = workbook.createSheet(sheetName);
		        // Create Row
	            //row = sheet.createRow(rowNumber-1);
	            	            
	            // Create cell and write
		        row = sheet.createRow(rowNumber-1);
	            cell = row.createCell(columnNumber-1);
	            //update existing cell value
	            //cell = sheet.getRow(rowNumber-1).getCell(columnNumber-1);
	            cell.setCellValue(dataToWrite);
	            
	            filePathWithExtension = filePath+"/"+fileName+".xlsx";
	            OutputStream = new FileOutputStream(new File(filePathWithExtension));
	            
	            workbook.write(OutputStream);
	            //Get absolute path of file
	            //System.out.println("Data written on file "+filePathWithExtension);
	            
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Unable to write data to excel file "+filePathWithExtension);
			}
			finally
			{
				try {
					OutputStream.close();
					System.out.println("Successfully data written on file "+filePathWithExtension);
				} catch (IOException e) {
					System.out.println("Unable to write data to excel file "+filePathWithExtension);
					e.printStackTrace();
				}
	            //System.out.println(filePathWithExtension+" has been updated successfully");
			}
	}
}
	
	
	
	
	
	

