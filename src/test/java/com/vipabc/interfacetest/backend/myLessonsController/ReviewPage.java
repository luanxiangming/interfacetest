package com.vipabc.interfacetest.backend.myLessonsController;

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
 * Created by oliverluan on 08/03/2017.
 */
public class ReviewPage extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "课后复习", groups = {"functiontest"})
    public void reviewPage(Map<String, String> dataDriven) throws IOException {
        String cookies = NewLoginMsg.newLogin(hf, dataDriven.get("account"), dataDriven.get("password"), Boolean.valueOf(dataDriven.get("rememberMe")), Boolean.valueOf(dataDriven.get("fromIms")));
        hf.nextRequest();
        ReviewPageMsg.reviewPage(hf, cookies);
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("ResponseString")));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "reviewPage");
    }
}
