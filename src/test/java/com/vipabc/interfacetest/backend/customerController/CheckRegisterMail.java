package com.vipabc.interfacetest.backend.customerController;

import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 27/02/2017.
 */
public class CheckRegisterMail extends Env{
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "检查邮箱", groups = {"functiontest"})
    public void checkRegisterMail(Map<String, String> dataDriven) {
        CheckRegisterMailMsg.checkRegisterMail(hf, dataDriven.get("email"));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("State\":", ",\""), dataDriven.get("State"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("ErrorMessage\":", "}"), dataDriven.get("ErrorMessage"));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "checkRegisterMail");
    }

}
