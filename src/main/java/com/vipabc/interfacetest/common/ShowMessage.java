package com.vipabc.interfacetest.common;

import org.testng.Reporter;

public class ShowMessage {
	 public static  void showAssertMessage(String className,String assertType,String jsonPathExpress,String expectedResultDetail,String actualResultDetail){
		    Reporter.log("*******Case: "+className+" 失败的断言描述如下****************************************");
		    Reporter.log("断言类型是：" + assertType);
		    Reporter.log("断言表达式(JsonPath)是：" + jsonPathExpress);
		    Reporter.log("预期断言结果是：" + expectedResultDetail);
		    Reporter.log("实际断言结果是：" + actualResultDetail);
		    Reporter.log("**********************************************************************************\n");
	    }
}
