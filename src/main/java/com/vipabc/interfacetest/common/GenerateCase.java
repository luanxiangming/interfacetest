package com.vipabc.interfacetest.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author echoshi
 * 自动生成Case
 *
 */
public class GenerateCase {
	
	public static void generateCase(String clazzName,String rowNum) {
		String srcPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\vipabc\\interfacetest\\template\\case_template.txt";
		String destPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\vipabc\\interfacetest\\backend\\"+clazzName+".java";
		try{			
			BufferedReader br=new BufferedReader(new FileReader(new File(srcPath)));
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(destPath)));		
			
			StringBuffer sb=new StringBuffer();
			String line="";
			while((line=br.readLine())!=null){
				if(line.contains("<ClassName>")){
					line=line.replace("<ClassName>",clazzName);
				}else if(line.contains("<RowNum>")){
					line=line.replace("<RowNum>",rowNum);
				}
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			
			String str=sb.toString();		
			bw.write(str,0,str.length()-1);		
			bw.close();		
			System.out.println("自动生成用例 "+destPath+"成功");
		}catch(Exception e){
			e.printStackTrace();			
			System.out.println("自动生成用例 "+destPath+"失败");
		}		
	}
}
