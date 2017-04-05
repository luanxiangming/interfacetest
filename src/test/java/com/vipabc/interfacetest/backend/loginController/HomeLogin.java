package com.vipabc.interfacetest.backend.loginController;

import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 23/02/2017.
 */
public class HomeLogin extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "首页登陆", groups = {"functiontest"})
    public void homeLogin(Map<String, String> dataDriven) {
        HomeLoginMsg.login(hf);
        Assert.assertEquals(hf.getStatus(), 302);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("Status\":", ",\""), dataDriven.get("Status"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("PlainResult\":\"", ",\""), dataDriven.get("message"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("JsonResult\":\"", ",\""), dataDriven.get("result"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("ErrCode\":", "}"), dataDriven.get("error_code"));
    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "homeLogin");
    }

}
