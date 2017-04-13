package com.vipabc.interfacetest.backend.clientApiCaller;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class ActivateAccount extends Env {
    @Test(priority = 1, dataProvider = "data", description = "用户接口激活", groups = {"functiontest"})
    public void activateAccount(Map<String, String> dataDriven) {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult, "$..ClientSN", 1);
        String Randstr = JsonPathUtil.parseJsonPath(JsonResult, "$..Randstr", 1);
        htf.nextRequest();
        ActivateAccountMsg.activateAccount(htf, Integer.valueOf(ClientSN), Randstr);
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.saveParamLeftstrRightstr("Status\":", ",\""), dataDriven.get("Status"));
        Assert.assertEquals(htf.saveParamLeftstrRightstr("PlainResult\":", ",\""), dataDriven.get("PlainResult"));
        Assert.assertEquals(htf.saveParamLeftstrRightstr("JsonResult\":", ",\""), dataDriven.get("JsonResult"));
        Assert.assertEquals(htf.saveParamLeftstrRightstr("ErrCode\":", "}"), dataDriven.get("ErrCode"));

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "activateAccount");
    }
}
