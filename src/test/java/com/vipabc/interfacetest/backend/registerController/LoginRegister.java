package com.vipabc.interfacetest.backend.registerController;

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
public class LoginRegister extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "登陆注册", groups = {"functiontest"})
    public void loginRegister(Map<String, String> dataDriven) {
        int response = LoginRegisterMsg.loginRegister(hf, dataDriven.get("type"), dataDriven.get("new"));
        Assert.assertEquals(response, 302);
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "loginRegister");
    }
}
