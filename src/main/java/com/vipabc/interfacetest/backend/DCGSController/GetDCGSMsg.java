package com.vipabc.interfacetest.backend.DCGSController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 27/03/2017.
 */
public class GetDCGSMsg {
    public static void getDCGS(HttpFixture hf, int clientSn, int brandId)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("api.server","url")+"/InformationAPIimo/DCGS/GetDCGS";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"clientSn\":\"" + clientSn + "\", \"brandId\":\"" + brandId + "\"}";

        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
