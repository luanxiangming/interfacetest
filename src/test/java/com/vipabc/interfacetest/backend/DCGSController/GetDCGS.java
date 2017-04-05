package com.vipabc.interfacetest.backend.DCGSController;

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
 * Created by oliverluan on 27/03/2017.
 */
public class GetDCGS extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "客戶DCGS", groups = {"Information.Api 3.0"})
    public void getDCGS(Map<String, String> dataDriven) {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(hf, dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult, "$..ClientSN", 1);
        hf.nextRequest();

        GetDCGSMsg.getDCGS(hf, Integer.valueOf(ClientSN), Integer.valueOf(dataDriven.get("brandId")));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("ClientSn\":", ",\""), ClientSN);

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getDCGS");
    }

}
