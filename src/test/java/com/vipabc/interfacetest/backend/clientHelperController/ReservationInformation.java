package com.vipabc.interfacetest.backend.clientHelperController;

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
 * Created by oliverluan on 21/03/2017.
 */
public class ReservationInformation extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "客户预订信息", groups = {"functiontest"})
    public void reservationInformation(Map<String, String> dataDriven) throws IOException {
        String cookies = NewLoginMsg.newLogin(hf, dataDriven.get("account"), dataDriven.get("password"), Boolean.valueOf(dataDriven.get("rememberMe")), Boolean.valueOf(dataDriven.get("fromIms")));
        hf.nextRequest();

        ReservationInformationMsg.reservationInformation(hf, cookies);
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("ResponseString")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("One-on-two")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("OneToThree")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("SmallClass")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("One-on-one")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("QuickSession")));
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("LobbyClass")));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "reservationInformation");
    }
}
