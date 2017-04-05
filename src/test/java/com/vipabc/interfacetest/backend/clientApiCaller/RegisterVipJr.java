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
public class RegisterVipJr extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "用户接口注册", groups = {"functiontest"})
    public void registerVipJr(Map<String, String> dataDriven) {
        RegisterVipjrMsg.registerVipjr(hf, Integer.valueOf(dataDriven.get("age")), Integer.valueOf(dataDriven.get("brandId")), dataDriven.get("cname"), dataDriven.get("ename"), dataDriven.get("countryId"), dataDriven.get("email"), Integer.valueOf(dataDriven.get("gender")), dataDriven.get("parentMail"), dataDriven.get("password"), dataDriven.get("mobile"), dataDriven.get("reasonId"), dataDriven.get("SourceType"), dataDriven.get("birthday"), dataDriven.get("phone"), Integer.valueOf(dataDriven.get("parentFlag")));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("success\":", ",\""), dataDriven.get("success"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("message\":", ",\""), dataDriven.get("message"));
    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "registerVipJr");
    }
}
