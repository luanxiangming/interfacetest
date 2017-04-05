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
 * Created by oliverluan on 07/03/2017.
 */
public class NewLogin extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "用户登陆", groups = {"functiontest"})
    public void newLogin(Map<String, String> dataDriven) {
        NewLoginMsg.newLogin(hf, dataDriven.get("account"), dataDriven.get("password"), Boolean.valueOf(dataDriven.get("rememberMe")), Boolean.valueOf(dataDriven.get("fromIms")));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("State\":", ",\""), dataDriven.get("State"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("redirectUrl\":", "}"), dataDriven.get("redirectUrl"));
        Assert.assertNotNull(hf.saveParamLeftstrRightstr("PlainText\":", ",\""));
    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "newLogin");
    }
}
