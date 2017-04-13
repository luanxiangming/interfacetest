package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Map;

public class SetReservationRegular extends Env {
    int MaxMember, SessionPeriod, sstNumber, IsCycleBooking = 0;
    String SessionTime = DateUtils.getReservationTime(2);

    @Test(priority = 1, dataProvider = "normal", description = "预订小班课课程", groups = {"functiontest"})
    public void setReservationRegular(Map<String, String> dataDriven) throws Exception {
        String account = accounts.get(dataDriven.get("account"));
        String password = accounts.get("pwd");
        SessionPeriod = Integer.valueOf(dataDriven.get("SessionPeriod"));
        MaxMember = Integer.valueOf(dataDriven.get("MaxMember"));
        sstNumber = Integer.valueOf(dataDriven.get("sstNumber"));
        IsCycleBooking = Integer.valueOf(dataDriven.get("IsCycleBooking"));

        NewLoginMsg.login(account, password);
        SetReservationMsg.reservationRegular(0, SessionTime, SessionPeriod, MaxMember, 0);
        Assert.assertEquals(htf.getStatus(), 200);
        for (Object body : Arrays.asList(htf.getValue())) {
            Assert.assertEquals(htf.getValue(body, ".Success"), true);
            Assert.assertEquals(htf.getValue(body, ".SstNumber"), sstNumber);
        }

    }

    @AfterMethod
    public void afterTest() {
        System.out.println("afterTest:" + getClass().getName());
        SetReservationMsg.reservationRegular(2, SessionTime, SessionPeriod, MaxMember, IsCycleBooking);
        for (Object body : Arrays.asList(htf.getValue())) {
            Assert.assertEquals(htf.getValue(body, ".Success"), true);
        }
    }
}
