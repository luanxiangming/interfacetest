package com.vipabc.interfacetest.backend.registerController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 23/02/2017.
 */
public class LoginRegisterMsg {
    public static int loginRegister(HttpFixture hf, String type, String newCode)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("url.server","url")+"/login/register?type="+type+"&new="+newCode;
        hf.setHeaderValue("Content-Type", "application/json");
        hf.setUrl(request);
        System.out.println(request);
        hf.setRequestBody("{\"class\":\"apply\"}");
        hf.Post();
        System.out.println("Response code: " + hf.getStatus());
        return hf.getStatus();
    }
}
