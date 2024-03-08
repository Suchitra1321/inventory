package com.genericUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils 
{
	/**
	 * 
	 * @param sheetName
	 * @param row
	 * @param cel
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @author User
	 * @return 
	 */
	public String readDataFromExcel(String sheetName, int row, int cel) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		 Sheet sh = book.getSheet(sheetName);
		String value = sh.getRow(row).getCell(cel).getStringCellValue();
		return value;
	}
	/**
	 * 
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastRowNo(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}
	
	/**
	 * 
	 * @param sheetName
	 * @param row
	 * @param cel
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetName,int row,int cel,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		book.getSheet(sheetName).getRow(row).createCell(cel).setCellValue(value);//whenever we create sheet then only we can create rows
		FileOutputStream fout = new FileOutputStream(IpathConstants.ExcelPath);
		book.write(fout);
		book.close();	
	}
	
	/**
	 * 
	 * @param sheetName
	 * @param cel
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public HashMap<String, String> readMultipleDataFromExcel(String sheetName,int cel) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook book = WorkbookFactory.create(fis);
		int rowCount = book.getSheet(sheetName).getPhysicalNumberOfRows();
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0;i<rowCount;i++)
		{
			String key = book.getSheet(sheetName).getRow(i).getCell(cel).getStringCellValue();
			String value = book.getSheet(sheetName).getRow(i).getCell(cel+1).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}
	
	
	public  Object[][] excelData(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\ExcelSales.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheet);
		int lastRow = sh.getLastRowNum()+1;
		int lastCell = sh.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
		
	}
}
