package com.vipabc.interfacetest.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtil {
	public static Document doc;
	public static Elements eles;
	
	/**
	 * @param htmlString  
	 * @param cssSelector
	 * @return
	 */
	public static Elements getElementsByCssSelector(String htmlString,String cssSelector){
		try{
			Document doc=Jsoup.parse(htmlString);
			eles=doc.select(cssSelector);	
			return eles;
		}catch(Exception e){
			System.out.println("根据cssSelector解析失败："+cssSelector);
		}finally {
			
		}	
		return null;
	}
	
	
}
