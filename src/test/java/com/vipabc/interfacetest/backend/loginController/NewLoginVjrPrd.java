package com.vipabc.interfacetest.backend.loginController;


import com.vipabc.interfacetest.utils.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Listeners({ com.vipabc.interfacetest.utils.AssertionListener.class })
public class NewLoginVjrPrd extends Env {
    @Test(priority = 1, dataProvider = "data", description = "Vjr登陆", groups = {"functiontest"})
    public void newLoginVjrPrd(Map<String, String> dataDriven) throws Exception {
        Pro pro=new Pro();
        String url=pro.getEnvPropties("url.server.jr","url")+"/Login/NewLogin";
        Map<String,String> bodyMap=new HashMap<>();
        bodyMap.put("Account",dataDriven.get("Account"));
        bodyMap.put("Password",dataDriven.get("Password"));
        bodyMap.put("RememberMe",dataDriven.get("RememberMe"));
        bodyMap.put("FromIms",dataDriven.get("FromIms"));

        String respones= HttpClientUtil.postMethodWithForm(url,bodyMap);
        Assertion.verifyEquals(JsonPathUtil.parseJsonPath(respones,"$..State",1),"LOGIN_SUCCESS");
        Assertion.verifyContains(JsonPathUtil.parseJsonPath(respones,"$..redirectUrl",1),"member.vipjr.com/aspx/IMO123");
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "newLoginVjrPrd");
    }
}
