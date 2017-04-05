package com.vipabc.interfacetest.backend.clientController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetClientInfoByIdMsg {
    public static void getClientInfoById(HttpFixture hf, String sn)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("apistore.server","url")+"/VMD/VipJr.API/api/Client/GetClientInfoById";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"sn\":\"" + sn + "\"}";
        hf.setRequestBody(reqBody);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
