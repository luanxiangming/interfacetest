package com.vipabc.interfacetest.backend.customerController;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 09/03/2017.
 */
public class ActivatingAccount extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "用户页面激活", groups = {"functiontest"})
    public void activatingAccount(Map<String, String> dataDriven) {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(hf, dataDriven.get("email")).replaceAll("\\\\", "");
        String checkid = JsonPathUtil.parseJsonPath(JsonResult, "$..Randstr", 1);
        hf.nextRequest();
        ActivatingAccountMsg.activatingAccount(hf, dataDriven.get("email"), checkid);
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("success")));
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "activatingAccount");
    }
}
