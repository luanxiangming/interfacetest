package com.vipabc.interfacetest.backend.bookingController;

import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SetReservationCustomize extends Env {
  
    @Test(priority = 1, dataProvider = "data", description = "定制一对一Plus", groups = {"functiontest"})
    public void setReservationCustomize(Map<String, String> dataDriven) throws IOException {
      
    	String actionType=dataDriven.get("ActionType");
    	String account = dataDriven.get("Account");
        String password = dataDriven.get("Password");  
        String sstNumber=dataDriven.get("SstNumber");
        String sessionTime=DateUtils.getReservationTimeIn30Min(24);
        String lobbySn=dataDriven.get("LobbySn"); 
        String SessionPeriod=dataDriven.get("SessionPeriod"); 
        String bookingClassType=dataDriven.get("BookingClassType"); 
        String maxMember=dataDriven.get("MaxMember");       
      

        NewLoginMsg.login(account, password);
        SetReservationCustomizeMsg.setReservationCustomize(actionType, sstNumber, sessionTime, lobbySn, SessionPeriod, bookingClassType, maxMember);
        
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.getValue(".Success"), true);
        Assert.assertEquals(JsonPathUtil.parseJsonPath(htf.Body, "$..SstNumber", 1), String.valueOf(80));
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setReservationCustomize");
    }

    @AfterMethod
    public void afterTest() {
//        System.out.println("afterTest:" + getClass().getName());
//        SetReservationMsg.reservationRegular(2, SessionTime, SessionPeriod, MaxMember);
//        Assert.assertEquals(htf.getValue(".Success"), true);
    }
}
