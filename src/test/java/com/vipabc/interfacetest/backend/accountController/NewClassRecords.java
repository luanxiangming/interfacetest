package com.vipabc.interfacetest.backend.accountController;

import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 24/03/2017.
 */
public class NewClassRecords extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "学习历史明细", groups = {"functiontest"})
    public void newClassRecords(Map<String, String> dataDriven) throws IOException {
        String cookies = NewLoginMsg.newLogin(hf, dataDriven.get("account"), dataDriven.get("password"), Boolean.valueOf(dataDriven.get("rememberMe")), Boolean.valueOf(dataDriven.get("fromIms")));
        hf.nextRequest();

        ClassContractMsg.classContract(hf, cookies);
        String contractSn = hf.saveParamLeftstrRightstr("csn=\"", "\"");
        hf.nextRequest();

        NewClassRecordsMsg.newClassRecords(hf, contractSn, cookies);
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("ResponseString")));
        Assert.assertTrue(hf.findStringinResponse("{\"List\":[{\"Sn\":"));


    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "newClassRecords");
    }
}
