package com.vipabc.interfacetest.backend.commonController;

import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetValidateCode extends Env{
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "生成验证码", groups = {"functiontest"})
    public void getValidateCode(Map<String, String> dataDriven) {
        String currentTime = DateUtils.getDateToString_simple();
        System.out.println("ValidateCode generated at: " + currentTime);
        GetValidateCodeMsg.getValidateCode(hf, currentTime);
        Assert.assertEquals(hf.getStatus(), 200);

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getValidateCode");
    }
}
