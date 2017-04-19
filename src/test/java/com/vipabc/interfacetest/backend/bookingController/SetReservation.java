package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.classController.EnterSessionMsg;
import com.vipabc.interfacetest.backend.classController.GetClientClassesMsg;
import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.io.IOException;
import java.util.*;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class SetReservation extends Env {

    int actionType, SstNumber, LobbySn, SessionPeriod, MaxMember, IsCycleBooking = 0;
    int day = 0;
    private String SessionTime;
    private String BookingClassType;
    private List<String> SessionSnList = new ArrayList<>();
    private List<String> SessionTimeList = new ArrayList<>();


    @Test(priority = 1, dataProvider = "data", description = "预订课程", groups = {"functiontest"}, timeOut = 10000)
    public void setReservation(Map<String, String> dataDriven) throws IOException {
        NewLoginMsg.login(dataDriven.get("account"), dataDriven.get("password"));

        actionType = Integer.valueOf(dataDriven.get("actionType"));
        SstNumber = Integer.valueOf(dataDriven.get("SstNumber"));
        LobbySn = Integer.valueOf(dataDriven.get("LobbySn"));
        SessionPeriod = Integer.valueOf(dataDriven.get("SessionPeriod"));
        BookingClassType = dataDriven.get("BookingClassType");
        MaxMember = Integer.valueOf(dataDriven.get("MaxMember"));
        IsCycleBooking = Integer.valueOf(dataDriven.get("IsCycleBooking"));

        SetReservationMsg.reservation(actionType, SstNumber, SessionTime, LobbySn, SessionPeriod, BookingClassType, MaxMember, IsCycleBooking);
        Assert.assertEquals(htf.getStatus(), 200);
        if (day == 0){
            for (Object body : Arrays.asList(htf.getValue())) {
                Assert.assertEquals(htf.getValue(body, ".Success"), false);
                Assert.assertEquals(htf.getValue(body, ".SstNumber"), 0);
            }
        } else {
            for (Object body : Arrays.asList(htf.getValue())) {
                Assert.assertEquals(htf.getValue(body, ".Success"), true);
                Assert.assertEquals(htf.getValue(body, ".SstNumber"), Integer.valueOf(dataDriven.get("SstNumber")));
            }
        }

        if (day != 0) {
            GetClientClassesMsg.getClientClasses(brandId, Integer.valueOf(dataDriven.get("ClientSn")), "8191", 64, SessionTime, SessionTime);

            for (Object body : Arrays.asList(htf.getValue())) {
                for ( int i = 0; i < htf.findNumberofStringinResponse("SessionSn"); i++) {
                    SessionSnList.add(htf.getValue(body, ".SessionSn", i).toString());
                    SessionTimeList.add(htf.getValue(body, ".BeginDateTime", i).toString().replaceAll("T", " "));
                }
            }

            System.out.println("SessionSnList: " + SessionSnList.toString());
            System.out.println("SessionTimeList: " + SessionTimeList.toString());

            for(String SessionSn : SessionSnList){
                EnterSessionMsg.enterSession(brandId, Integer.valueOf(dataDriven.get("ClientSn")),false, SessionSn);
                for (Object body : Arrays.asList(htf.getValue())) {
                    Assert.assertEquals(htf.getStatus(), 200);
                    Assert.assertEquals(htf.getValue(body, ".SessionSn"), SessionSn);
                }
            }
        }

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setReservation");
    }

    @BeforeMethod(description = "课程预订测试开始：随机生成1周内的订课时间")
    public void beforeTest() {
        SessionSnList.clear();
        SessionTimeList.clear();
        day = new Random().nextInt(8);
        SessionTime = DateUtils.getNextCurrent(day);
    }

    @AfterMethod(description = "课程预订测试结束：取消订课操作")
    public void afterTest() {
        for (String sessionTime : SessionTimeList) {
            SetReservationMsg.reservation(2, 0, sessionTime, LobbySn, SessionPeriod, BookingClassType, 0, 0);
            Assert.assertEquals(htf.getValue(".Success"), true);
        }
    }
}
