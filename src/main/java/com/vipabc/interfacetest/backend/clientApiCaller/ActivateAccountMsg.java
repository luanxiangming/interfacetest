package com.vipabc.interfacetest.backend.clientApiCaller;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class ActivateAccountMsg {
    public static void activateAccount(HttpFixture hf, int ClientSN, String Randstr)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("apistore.server","url")+"/VMD/VipJr.API/api/Client/ActivateAccount";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"ClientSN\":\"" + ClientSN + "\", \"Randstr\":\"" + Randstr + "\"}";
        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
