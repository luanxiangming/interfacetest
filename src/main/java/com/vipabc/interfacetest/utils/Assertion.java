package com.vipabc.interfacetest.utils;

import java.util.ArrayList;
import java.util.List; 
import org.testng.Assert;
 
/**
 * 本类封装TestNG的assert
 * 使得testNg在断言失败后，继续执行其他断言
 */
public class Assertion {
     
	//Case结果标志位，默认执行失败
    public static boolean flag = false; 
    
    //错误信息
    public static List<Error> errors = new ArrayList<Error>();


    
    /**
     * 	Assert.fail();
     *  使用assertSetFlag(false)代替
     */
    public static boolean assertSetFlag(Boolean flag1){
    	flag=flag1;
    	return flag;    
    }
     
    /**
     * 相等匹配    
     */
    public static boolean verifyEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
            return true;
        }catch(Error e){
            errors.add(e);
            flag = false;
            return false;
        }
    }
     
    /**
     * 相等匹配    
     */
    public static boolean verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
            return true;
        }catch(Error e){
            errors.add(e);
            flag = false;
            return false;
        }
    } 
    
    /**
     * 包含匹配    
     */
    public static boolean verifyContains(Object actual, Object expected){
    	try{
    		if(String.valueOf(actual).contains(String.valueOf(expected))){    			
        		return true;
        	}else {        		
                flag = false;               
        		return false;
        	}
    	}catch(Error e){
    		 errors.add(e);
             flag = false;
             return false;
    	}    	
    } 
    
    /**
     * 包含匹配    
     */
    public static boolean verifyHTMLContains(Object actual, Object expected){
    	try{
    		if(String.valueOf(actual).contains(String.valueOf(expected))){    			
        		return true;
        	}else {        		
                flag = false;               
        		return false;
        	}
    	}catch(Error e){
    		 errors.add(e);
             flag = false;
             return false;
    	}    	
    } 
    
    
    
    /**
     * 正则表达式匹配 
     */
    public static boolean verifyRegex(Object actual, String expected){
    	try{
    		expected=dealBackSlant(expected);
    		if(String.valueOf(actual).matches(expected)){
        		return true;
        	}else {
        		flag = false;        		
        		return false;
        	}
    	}catch(Error e){
    		 errors.add(e);
             flag = false;
             return false;
    	}    	
    }
    
    
    /**
     * @param str 单元个，读取到"\\"时，会显示"\\\\"
     * 示例：regex ## $..Data.marketMediaInfo..sn ## \\d+
     */
    public static String dealBackSlant(String str){    
    	while(str.contains("\\\\")){
    		str=str.replace("\\\\", "\\");    		
    	}
    	return str;
    }
}