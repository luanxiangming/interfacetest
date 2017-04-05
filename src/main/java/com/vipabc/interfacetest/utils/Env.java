package com.vipabc.interfacetest.utils;

import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import shelper.environment.Environment;
import com.vipabc.interfacetest.backend.BaseMsg;
import shelper.iffixture.HttpFixture;


public class Env {
    public static String qa_test_home;
    public static HttpFixture htf = null;
    public static JsonPathUtil jsonUtil = null;
    public static ConnectToDB ctd = null;
    public static String db = "vfs";

    @BeforeClass
    public void Env() {
        if (System.getProperty("qa_test_home") == null) {
            System.setProperty("qa_test_home", ".");
        }
        qa_test_home = System.getProperty("qa_test_home");
        Environment.set4If();
        htf = new HttpFixture();
        BaseMsg.setHttpFixture(htf);
        ctd = new ConnectToDB();
    }

    @AfterClass
    public void tearDown() {
        ctd.closeDBcon();
    }

    @AfterMethod
    public void afterMethod() {
        htf.quit();
    }

}
