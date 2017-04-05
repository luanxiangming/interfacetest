package com.vipabc.interfacetest.backend.customerController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 27/02/2017.
 */
public class CheckRegisterMailMsg {
    public static void checkRegisterMail(HttpFixture hf, String email)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("url.server","url")+"/Customer/CheckRegisterMail?email="+email;
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }

}
