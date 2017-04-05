package com.vipabc.interfacetest.backend.levelController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 29/03/2017.
 */
public class GetLevelInfoMsg {
    public static void getLevelInfo(HttpFixture hf, int brandId, int clientSn, int gradeType)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("api.server","url")+"/InformationAPIimo/Level/GetLevelInfo";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"brandId\":\"" + brandId + "\", \"clientSn\":\"" + clientSn + "\", \"gradeType\":\"" + gradeType + "\"}";

        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
