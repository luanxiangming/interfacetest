package com.vipabc.interfacetest.backend.clientApiCaller;

import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class LoginVipjr extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "用户接口登陆", groups = {"functiontest"})
    public void loginVipjr(Map<String, String> dataDriven) {
        LoginVipjrMsg.loginVipjr(hf, dataDriven.get("account"), dataDriven.get("password"));
        Assert.assertEquals(hf.getStatus(), 200);

        Assert.assertEquals(hf.saveParamLeftstrRightstr("success\":", ",\""), dataDriven.get("success"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("clientSn\":", ",\""), dataDriven.get("clientSn"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("cname\":", ",\""), dataDriven.get("cname"));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "loginVipjr");
    }


}
