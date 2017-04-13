package com.vipabc.interfacetest.backend.loginController;


import com.vipabc.interfacetest.utils.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({ com.vipabc.interfacetest.utils.AssertionListener.class })
public class HomeLoginVipabcPrd extends Env {
    @Test(priority = 1, dataProvider = "data", description = "Vipabc登陆", groups = {"functiontest"})
    public void homeLoginVipabcPrd(Map<String, String> dataDriven) throws Exception {
        Pro pro=new Pro();
        // "Home/Login"请求
        String url=pro.getEnvPropties("url.server.vipabc","url")+"/Home/Login";
        // Center/Login/LoginTransForm?chk_remember_account=N&returnurl=null请求，该请求设置cookie
        String url2=pro.getEnvPropties("url.server.vipabc","url")+"/Center/Login/LoginTransForm?chk_remember_account=N&returnurl=null";

        // "Home/Login"请求
        Map<String,String> bodyMap=new HashMap<>();
        bodyMap.put("txt_login_account",dataDriven.get("txt_login_account"));
        bodyMap.put("pwd_login_password",dataDriven.get("pwd_login_password"));
        String respones=HttpClientUtil.postMethodWithForm(url,bodyMap);

        Assertion.verifyEquals(JsonPathUtil.parseJsonPath(respones,"$..Status",1),"OK");
        //验证返回数字
        Assertion.verifyRegex(JsonPathUtil.parseJsonPath(respones,"$..PlainResult",1),"^[0-9]*$");

        // Center/Login/LoginTransForm?chk_remember_account=N&returnurl=null请求，该请求设置cookie
        String respones2=HttpClientUtil.postMethodWithForm(url2,bodyMap);
        Assertion.verifyEquals(HttpClientUtil.httpResponse.getStatusLine().getStatusCode(),302);
        Assertion.verifyContains(respones2,"Object moved to <a href=\"/Center3.0\"");
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "homeLoginVipabcPrd");
    }
}
