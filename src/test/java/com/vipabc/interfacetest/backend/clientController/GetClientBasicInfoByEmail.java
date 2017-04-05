package com.vipabc.interfacetest.backend.clientController;

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
public class GetClientBasicInfoByEmail extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "按用户Email查看用户信息", groups = {"functiontest"})
    public void getClientBasicInfoByEmail(Map<String, String> dataDriven) {
        GetClientBasicInfoByEmailMsg.getClientInfoByEmail(hf, dataDriven.get("email"));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertEquals(hf.saveParamLeftstrRightstr("Status\":", ",\""), dataDriven.get("Status"));
        Assert.assertEquals(hf.saveParamLeftstrRightstr("ErrCode\":", "}"), dataDriven.get("ErrCode"));

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getClientBasicInfoByEmail");
    }
}
