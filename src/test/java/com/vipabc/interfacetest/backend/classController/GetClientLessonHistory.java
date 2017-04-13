package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class GetClientLessonHistory extends Env {
    @Test(priority = 1, dataProvider = "data", description = "课后复习列表获取", groups = {"Information.Api 3.0"})
    public void getClientLessonHistory(Map<String, String> dataDriven) throws IOException {
        String JsonResult = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult, "$..ClientSN", 1);
        htf.nextRequest();

        String BeginTime = DateUtils.defaultFormatDate(DateUtils.addHours(new Date(), 1));
        String EndTime = DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), 3));

        GetClientLessonHistoryMsg.getClientLessonHistory(Integer.valueOf(dataDriven.get("brandId")), Integer.valueOf(ClientSN), Integer.valueOf(dataDriven.get("lessonType")), dataDriven.get("searchType"), BeginTime, EndTime);
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertEquals(htf.saveParamLeftstrRightstr("ClientSn\":", ","), ClientSN);
        Assert.assertTrue(htf.findStringinResponse("\"ClassInfo\":{"));

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "getClientLessonHistory");
    }

}
