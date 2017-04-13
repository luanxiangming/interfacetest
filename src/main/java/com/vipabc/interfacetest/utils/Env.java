package com.vipabc.interfacetest.utils;

import com.vipabc.interfacetest.backend.BaseMsg;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import shelper.datadrive.ExcelProvider;
import shelper.environment.Environment;
import shelper.iffixture.HttpFixture;

import java.io.File;
import java.util.Iterator;
import java.util.Map;


public class Env {
    public static String qa_test_home;
    public static Pro prop = new Pro();
    public static HttpFixture htf = null;
    public static int brandId = 0;
    public static String brand = "";
    public boolean flag;
    public Throwable thro;
    public static String caseName = "";
    public static Map<String, String> accounts;
//    JavaMail javaMail = new JavaMail(true);
//    public static String failCountPath = "C:\\failCount.properties";
    public static String failCountPath = System.getProperty("user.dir") + File.separator + "failCount.properties";
    public static Map<String, Integer> failCount = (Map) prop.getEnvPropties(failCountPath);
    public static boolean haveOverCount = false;
    public static ConnectToDB ctd = null;

    @BeforeClass
    public void Env() {
        if (System.getProperty("qa_test_home") == null) {
            System.setProperty("qa_test_home", ".");
        }
        qa_test_home = System.getProperty("qa_test_home");
        Environment.set4If();
//        brandId = Integer.valueOf(prop.getEnvPropties("brandId", "env"));
        brandId = Integer.valueOf(prop.getEnvPropties("brandId", "env"));
        brand = brandId == 4 ? "vipabcjr" : "vipabc";
        accounts = (Map) prop.getEnvPropties(brandId, "accounts");
        htf = new HttpFixture();
        BaseMsg.setHttpFixture(htf);
        ctd = new ConnectToDB();
        BaseMsg.setConnectToDB(ctd);

    }

    @AfterClass
    public void tearDown(ITestContext context) {
        String methodName = this.getClass().getMethods()[1].getName();
        if (failCount.get(methodName) == null) {
            failCount.put(methodName, 0);
        }
        String cn = "";
        int failNum = Integer.parseInt(String.valueOf(failCount.get(methodName)));
        if (context.getFailedTests().getAllResults().toArray().length != 0) {
            ITestResult re = (ITestResult) context.getFailedTests().getAllResults().toArray()[0];
            cn = re.getMethod().getDescription();
        }

        if (cn.length() > 0) {
            failCount.put(methodName, (failNum + 1));
            prop.setEnvPropties(failCountPath, failCount);
            if (failNum + 1 > 3) {
                haveOverCount = true;
            }
            caseName = caseName + cn + " ";
        } else {
            failCount.put(methodName, 0);
            prop.setEnvPropties(failCountPath, failCount);
        }
        ctd.closeDBcon();
    }

    @AfterSuite
    public void afterSuite(ITestContext context) {
        if (caseName.length() > 0 && !haveOverCount) {
//            javaMail.sendSMS(caseName + " :执行失败！详情请参考报告http://172.16.233.59:9090/report-dev/Report.html");
            System.out.println(caseName + " :执行失败！详情请参考报告http://172.16.233.59:9090/report-dev/Report.html");
        }
        else
        {
            System.out.println("执行成功，或连续失败3次以上，不发送短信");
        }
    }

    @AfterMethod
    public void afterMethod() {
        htf.quit();
    }

    @DataProvider(name = "normal")
    public Iterator<Object[]> normal() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, this.getClass().getMethods()[1].getName(), brand);
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @BeforeSuite
    public void beforeSuite() {
    }
}

