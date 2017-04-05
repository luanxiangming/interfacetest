package com.vipabc.interfacetest.backend.afterClassController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class GetAfterQuestionMsg {
    public static String getAfterQuestion(HttpFixture hf, int clientSn, String sessionSn, String brandId)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("api.server","url")+"/InformationAPIimo/AfterClass/GetAfterQuestion";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"brandId\":\"" + brandId + "\", \"clientSn\":" + clientSn +  ", \"sessionSn\":\"" + sessionSn + "\"}";

        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
        String JsonResult = hf.saveParamLeftstrRightstr("[", "]");
        return JsonResult;
    }
}
