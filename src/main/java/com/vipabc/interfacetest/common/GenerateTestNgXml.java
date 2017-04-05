package com.vipabc.interfacetest.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class GenerateTestNgXml {
	public static void generateTestNgXml(List<String> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<suite name=\"Suite\" parallel=\"false\"  preserve-order=\"true\">\n");		

		for (String str : list) {
			sb.append("	<test name=\"").append(str).append("\">\n" + "		<classes>\n")
					.append("			<class name=\"com.vipabc.interfacetest.backend.").append(str)
					.append("\"/>\n" + "		</classes>\n" + "	</test>\n");
		}
		sb.append("	<listeners>\n" + "		<listener class-name=\"org.uncommons.reportng.HTMLReporter\" />\n"
				+ "		<listener class-name=\"org.uncommons.reportng.JUnitXMLReporter\" />\n" + "	</listeners>\n"
				+"	<usedefaultlisteners name=\"false\" />\n"
				+ "</suite> <!-- Suite -->");

		try {
			String destPath = System.getProperty("user.dir") + File.separator + "testng.xml";

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(destPath)));
			String str = sb.toString();
			bw.write(str, 0, str.length());
			bw.close();

			System.out.println("自动生成testng.xml成功");
		} catch (Exception e) {
			System.out.println("自动生成testng.xml失败");
			e.printStackTrace();
		}
	}
}
