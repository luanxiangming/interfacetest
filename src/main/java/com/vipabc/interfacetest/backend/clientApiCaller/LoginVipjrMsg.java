package com.vipabc.interfacetest.backend.clientApiCaller;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class LoginVipjrMsg {
    public static void loginVipjr(HttpFixture hf, String account, String password)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("login.server","url")+"/loginapi/in";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"account\":\"" + account + "\", \"password\":\"" + password + "\"}";
        hf.setRequestBody(reqBody);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
