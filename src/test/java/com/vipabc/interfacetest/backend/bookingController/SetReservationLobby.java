package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.classController.GetClientClassesMsg;
import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

public class SetReservationLobby extends Env {
    int brandId, clientSn, SessionPeriod, LobbySn, classType, sstNumber = 0;
    int searchType = 64;
    String BookingClassType, SessionTime;

    @Test(priority = 1, dataProvider = "normal", description = "预订讲堂课程", groups = {"functiontest"})
    public void setReservationLobby(Map<String, String> dataDriven) throws Exception {
        String account = accounts.get(dataDriven.get("account"));
        String password = accounts.get("pwd");
        brandId = Integer.valueOf(dataDriven.get("brandId"));
        clientSn = Integer.valueOf( accounts.get(dataDriven.get("clientSn")));
        SessionPeriod = Integer.valueOf(dataDriven.get("SessionPeriod"));
        BookingClassType = dataDriven.get("BookingClassType");
        sstNumber = Integer.valueOf(dataDriven.get("sstNumber"));
        String BeginTime = DateUtils.defaultFormatDate(DateUtils.addHours(new Date(), 1));
        String EndTime = DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), 7));

        GetClientClassesMsg.getClientClasses(brandId, clientSn, BookingClassType, searchType, BeginTime, EndTime);
        LobbySn = (Integer) htf.getValue(".ClassInfo.[?(@.SessionPeriod==" + SessionPeriod + ")].LobbySn");
        SessionTime = ((String) htf.getValue(".ClassInfo.[?(@.SessionPeriod==" + SessionPeriod + ")].BeginDateTime")).replace("T", " ");

        NewLoginMsg.login(account, password);
        SetReservationMsg.reservation(0, 0, SessionTime, LobbySn, SessionPeriod, BookingClassType, 0, 0);
        Assert.assertEquals(htf.getStatus(), 2001);
        Assert.assertEquals(htf.getValue(".Success"), true);
        Assert.assertEquals(htf.getValue(".SstNumber"), sstNumber);
        Assert.assertEquals(htf.getValue(".SstNumber"), sstNumber);
    }


    @AfterMethod
    public void afterTest() {
        System.out.println("setReservationLobby afterTest");
        SetReservationMsg.reservation(2, 0, SessionTime, LobbySn, SessionPeriod, BookingClassType, 0, 0);
        Assert.assertEquals(htf.getValue(".Success"), true);
    }

}
