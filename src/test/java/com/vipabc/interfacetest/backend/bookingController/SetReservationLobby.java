package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.classController.GetClientClassesMsg;
import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class SetReservationLobby extends Env {
    int brandId, clientSn, SessionPeriod, LobbySn, classType, sstNumber = 0;
    int searchType = 16;
    String BookingClassType, SessionTime;

    @Test(priority = 1, dataProvider = "data", description = "预订讲堂课程", groups = {"functiontest"})
    public void setReservationLobby(Map<String, String> dataDriven) throws IOException {
        String account = dataDriven.get("account");
        String password = dataDriven.get("password");
        brandId = Integer.valueOf(dataDriven.get("brandId"));
        clientSn = Integer.valueOf(dataDriven.get("clientSn"));
        SessionPeriod = Integer.valueOf(dataDriven.get("SessionPeriod"));
        BookingClassType = dataDriven.get("BookingClassType");

        String BeginTime = DateUtils.defaultFormatDate(DateUtils.addHours(new Date(), 1));
        String EndTime = DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), 7));
        switch (SessionPeriod) {
            case 10:
                sstNumber = 98;
                break;
            case 20:
                sstNumber = 97;
                break;
            case 45:
                sstNumber = 99;
        }

        GetClientClassesMsg.getClientClasses(brandId, clientSn, BookingClassType, searchType, BeginTime, EndTime);
//        LobbySn = (Integer) htf.getValue(".LobbySn");
        LobbySn = (Integer) htf.getValue(".ClassInfo.[?(@.SessionPeriod==" + SessionPeriod + ")].LobbySn");
        SessionTime = ((String) htf.getValue(".ClassInfo.[?(@.SessionPeriod==" + SessionPeriod + ")].BeginDateTime")).replace("T", " ");

        NewLoginMsg.newLoginV(account, password);
        SetReservationMsg.reservation(0, 0, SessionTime, LobbySn, SessionPeriod, BookingClassType, 0, 0);
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.getValue(".Success"), true);
        Assert.assertEquals(htf.getValue(".SstNumber"), sstNumber);
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setReservationLobby");
    }

    @AfterMethod
    public void afterTest() {
        System.out.println("setReservationLobby afterTest");
        SetReservationMsg.reservation(2, 0, SessionTime, LobbySn, SessionPeriod, BookingClassType, 0, 0);
        Assert.assertEquals(htf.getValue(".Success"), true);
    }
}
