package com.vipabc.interfacetest.backend.afterClassController;

import com.vipabc.interfacetest.backend.clientController.GetClientBasicInfoByEmailMsg;
import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class SetAfterQuestionEvaluation extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "儲存客戶課後問題評鑑", groups = {"Information.Api 3.0"})
    public void setAfterQuestionEvaluation(Map<String, String> dataDriven) {
        String JsonResult1 = GetClientBasicInfoByEmailMsg.getClientInfoByEmail(hf, dataDriven.get("email")).replaceAll("\\\\", "");
        String ClientSN = JsonPathUtil.parseJsonPath(JsonResult1, "$..ClientSN", 1);
        hf.nextRequest();

        String JsonResult2 = GetAfterQuestionMsg.getAfterQuestion(hf, Integer.valueOf(ClientSN), dataDriven.get("sessionSn"), dataDriven.get("brandId"));
        String Sn = JsonPathUtil.parseJsonPath(JsonResult2, "$..Sn", 1);
        hf.nextRequest();

        SetAfterQuestionEvaluationMsg.setAfterQuestionEvaluation(hf, Integer.valueOf(Sn), Integer.valueOf(dataDriven.get("evaluation")), dataDriven.get("brandId"));
        Assert.assertEquals(hf.getStatus(), 200);
        Assert.assertTrue(hf.findStringinResponse(dataDriven.get("responseString")));

    }
    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "setAfterQuestionEvaluation");
    }
}
