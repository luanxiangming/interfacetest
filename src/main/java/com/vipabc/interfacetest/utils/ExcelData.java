package com.vipabc.interfacetest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	private Workbook wb=null;
	private HashMap<String, SheetData> sheetDataMap=new HashMap<String, SheetData>();
	private int sheetNum=-1;
	
	public ExcelData(InputStream in) {
		try {
			wb= WorkbookFactory.create(in);
			sheetNum=wb.getNumberOfSheets();
			loadSheetDataMap();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	

	private void loadSheetDataMap() {
		// TODO Auto-generated method stub
		for(int sheetIndex=0;sheetIndex<sheetNum;sheetIndex++){
			String sheetName=wb.getSheetName(sheetIndex);
			SheetData sheetData=new SheetData(wb.getSheetAt(sheetIndex));
			sheetDataMap.put(sheetName, sheetData);
		}
	}
	
	public SheetData getSheetDataByName(String sheetName){
		return sheetDataMap.get(sheetName);		
	}
	
	public int getSheetIndex(String sheetName){
		int index=wb.getSheetIndex(sheetName);
		return index;
	}
	
	public String getSheetText(String sheetName,int row,int column){
		return getSheetDataByName(sheetName).getCellData(row, column);
	}	
}
