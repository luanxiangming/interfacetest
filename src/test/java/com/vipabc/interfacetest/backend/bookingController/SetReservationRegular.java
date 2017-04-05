package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.io.IOException;
import java.util.*;

public class SetReservationRegular extends Env {
    int MaxMember, SessionPeriod, sstNumber, IsCycleBooking = 0;
    String SessionTime = DateUtils.getReservationTime(2);

    @Test(priority = 1, dataProvider = "data", description = "预订小班课课程", groups = {"functiontest"})
    public void setReservationRegular(Map<String, String> dataDriven) throws IOException {
        String account = dataDriven.get("account");
        String password = dataDriven.get("password");
        SessionPeriod = Integer.valueOf(dataDriven.get("SessionPeriod"));
        MaxMember = Integer.valueOf(dataDriven.get("MaxMember"));
        IsCycleBooking = Integer.valueOf(dataDriven.get("IsCycleBooking"));
        switch (MaxMember) {
            case 1:
                sstNumber = 7;
                break;
            case 2:
                sstNumber = 2;
                break;
            case 3:
                sstNumber = 4;
                break;
            case 6:
                sstNumber = 6;
        }
//        NewLoginMsg.newLogin2(account, password);
        NewLoginMsg.newLoginV(account, password);
        SetReservationMsg.reservationRegular(0, SessionTime, SessionPeriod, MaxMember, 0);
        Assert.assertEquals(htf.getStatus(), 200);
        for (Object body : Arrays.asList(htf.getValue())) {
            Assert.assertEquals(htf.getValue(body, ".Success"), true);
            Assert.assertEquals(htf.getValue(body, ".SstNumber"), sstNumber);
        }
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setReservationRegular");
    }

    @AfterMethod
    public void afterTest() {
        System.out.println("afterTest:" + getClass().getName());
        SetReservationMsg.reservationRegular(2, SessionTime, SessionPeriod, MaxMember, 0);
        for (Object body : Arrays.asList(htf.getValue())) {
            Assert.assertEquals(htf.getValue(body, ".Success"), true);
        }
    }
}
