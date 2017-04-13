package com.vipabc.interfacetest.backend.DCGSController;

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
 * Created by oliverluan on 27/03/2017.
 */
public class GetDCGS extends Env {
    @Test(priority = 1, dataProvider = "data", description = "客戶DCGS", groups = {"Information.Api 3.0"})
    public void getDCGS(Map<String, String> dataDriven) {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult, "$..ClientSN", 1);
        htf.nextRequest();

        GetDCGSMsg.getDCGS(htf, Integer.valueOf(ClientSN), Integer.valueOf(dataDriven.get("brandId")));
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.saveParamLeftstrRightstr("ClientSn\":", ",\""), ClientSN);

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getDCGS");
    }

}
