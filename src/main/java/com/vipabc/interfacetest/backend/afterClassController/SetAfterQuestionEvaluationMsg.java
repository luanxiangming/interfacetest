package com.vipabc.interfacetest.backend.afterClassController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class SetAfterQuestionEvaluationMsg {
    public static void setAfterQuestionEvaluation(HttpFixture hf, int afterQuestionSn, int evaluation, String brandId)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("api.server","url")+"/InformationAPIimo/AfterClass/SetAfterQuestionEvaluation";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"brandId\":\"" + brandId + "\", \"afterQuestionSn\":" + afterQuestionSn +  ", \"evaluation\":" + evaluation + "}";

        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
