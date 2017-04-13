package com.vipabc.interfacetest.backend.afterClassController;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class SetAfterQuestionEvaluation extends Env {
    @Test(priority = 1, dataProvider = "data", description = "儲存客戶課後問題評鑑", groups = {"Information.Api 3.0"})
    public void setAfterQuestionEvaluation(Map<String, String> dataDriven) {
        String JsonResult1 = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult1, "$..ClientSN", 1);
        htf.nextRequest();

        String JsonResult2 = GetAfterQuestionMsg.getAfterQuestion(htf, Integer.valueOf(ClientSN), dataDriven.get("sessionSn"), dataDriven.get("brandId"));
        String Sn = JsonPathUtil.parseJsonPath(JsonResult2, "$..Sn", 1);
        htf.nextRequest();

        SetAfterQuestionEvaluationMsg.setAfterQuestionEvaluation(htf, Integer.valueOf(Sn), Integer.valueOf(dataDriven.get("evaluation")), dataDriven.get("brandId"));
        Assert.assertEquals(htf.getStatus(), 200);
        Assert.assertTrue(htf.findStringinResponse(dataDriven.get("responseString")));

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setAfterQuestionEvaluation");
    }
}
