//package com.vipabc.interfacetest;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.Reporter;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//import com.vipabc.interfacetest.common.ParseCellContent;
//import com.vipabc.interfacetest.utils.Assertion;
//import com.vipabc.interfacetest.utils.ExcelData;
//import com.vipabc.interfacetest.utils.HttpClientUtil;
//import com.vipabc.interfacetest.utils.JsonPathUtil;
//import com.vipabc.interfacetest.utils.SheetData;
//
//
//
//@Listeners({com.vipabc.interfacetest.utils.AssertionListener.class})
//public class RunCase {
//
//	public static SheetData sheetData;	
//	public  static List<String[]> expectedResultList = new ArrayList<String[]>();	
//	public static String responseStr="";
//
//	public static void loadExcel() {
////		String path = "d:\\" + "Http_Request_workbook_Data.xlsx";
//		String path="d:\\"+"Http_Request_workbook_Data.xlsx";
//		String sheetName = "Input";
//		ExcelData excelReader = null;
//
//		try {
//			InputStream in = new FileInputStream(path);
//			excelReader = new ExcelData(in);
//		} catch (FileNotFoundException e) {			
//			e.printStackTrace();
//		}
//		if (excelReader != null) {
//			sheetData = excelReader.getSheetDataByName(sheetName);
//		}
//	}
//
//	@BeforeClass
//	public void setUp() {
//		loadExcel();
//	}
//
//	@Test
//	public void test() {
//		for(int i=1;i<=sheetData.getRowCount();i++){
//			Map<String,String> maps=sheetData.getRowDataMap(i);
//			
//			String url="";
//			if(maps.get("Host").endsWith("/")){
//				url=maps.get("Host")+maps.get("RequsetSubpath");
//			}else{
//				url=maps.get("Host")+"/"+maps.get("RequsetSubpath");
//			}			
//			System.out.println(url);
//			
//			String jsonBody=maps.get("RequestParams");
//			System.out.println(jsonBody);
//			
//			String expectedResult = maps.get("ExpectedResult");
//			System.out.println(expectedResult);	
//			
//			try{
//				Class<?> clazz=HttpClientUtil.class;
//				Object obj=clazz.newInstance();
//				Method postMethodWithRawBody=clazz.getMethod("postMethodWithRawBody",String.class, String.class);
//				postMethodWithRawBody.invoke(obj, url,jsonBody);
//				Method getRespones=clazz.getMethod("getResponse");				
//				responseStr=(String) getRespones.invoke(obj);
//				System.out.println("########## Invoke response is:"+responseStr);
//				
//				ParseCellContent parseCellContent=new ParseCellContent(expectedResult);		
//				expectedResultList=parseCellContent.getExpectedResultList();
//				
//				for(int m=0;m<expectedResultList.size();m++){
//					String assertType=parseCellContent.getExpectedResultSubContent(m,0);
//					String jsonPathExpress=parseCellContent.getExpectedResultSubContent(m,1);
//					String expectedResultDetail=parseCellContent.getExpectedResultSubContent(m,2);
//					
//					Reporter.log("Case失败信息如下\n");
//					Reporter.log("断言类型是："+assertType+"、"+"JsonPath表达式是："+jsonPathExpress+"、"+"预期断言结果是："+expectedResultDetail+"\n");
//					Reporter.log("JsonPath解析内容是："+JsonPathUtil.parseJsonPath(responseStr, jsonPathExpress, 1));
//					System.out.print("断言类型是："+assertType+"、"); 
//					System.out.print("JsonPath表达式是："+jsonPathExpress+"、"); 
//					System.out.println("预期断言结果是："+expectedResultDetail); 	
//					System.out.println("第"+(m+1)+"次测试");
//					System.out.println("JsonPath解析内容是："+JsonPathUtil.parseJsonPath(responseStr, jsonPathExpress, 1));
//					Assertion.verifyEquals(JsonPathUtil.parseJsonPath(responseStr, jsonPathExpress, 1), expectedResultDetail,"预期结果跟实际结果比较");	
//					
//					Reporter.log("实际返回报文是:"+responseStr);
//				}				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			
//			System.out.println("===========================================================================================================================================================================================");
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {				
//				e.printStackTrace();
//			}
//		}		
//	}
//
//	@AfterClass
//	public void testDown() {
//
//	}
//}
