package com.vipabc.interfacetest.backend.clientController;

import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetClientBasicInfoByEmail extends Env {
    @Test(priority = 1, dataProvider = "data", description = "按用户Email查看用户信息", groups = {"functiontest"})
    public void getClientBasicInfoByEmail(Map<String, String> dataDriven) {
        GetClientBasicInfoByEmailMsg.getClientInfoByEmail(dataDriven.get("email"));
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.saveParamLeftstrRightstr("Status\":", ",\""), dataDriven.get("Status"));
        Assert.assertEquals(htf.saveParamLeftstrRightstr("ErrCode\":", "}"), dataDriven.get("ErrCode"));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getClientBasicInfoByEmail");
    }
}
