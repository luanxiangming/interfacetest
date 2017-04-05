package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.io.IOException;
import java.util.*;

/**
 * Created by oliverluan on 05/04/2017.
 */
public class GetClientClassRecordTimeList extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "取得顧客的上課紀錄: 我的课程-日历表", groups = {"Information.Api 3.0"})
    public void getClientClassRecordTimeList(Map<String, String> dataDriven) throws IOException {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(hf, dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult, "$..ClientSN", 1);
        hf.nextRequest();

        String BeginDate = DateUtils.defaultFormatDate(DateUtils.addHours(new Date(), 1));
        String EndDate = DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), 365));

        GetClientClassRecordTimeListMsg.getClientClassRecordTimeList(Integer.valueOf(dataDriven.get("brandId")), Integer.valueOf(ClientSN), BeginDate, EndDate);
        Assert.assertEquals(htf.getStatus(), 200);
        List<String> listA = Arrays.asList(htf.getResponseBody());
        Assert.assertEquals(listA.size(), 1);
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getClientClassRecordTimeList");
    }
}
