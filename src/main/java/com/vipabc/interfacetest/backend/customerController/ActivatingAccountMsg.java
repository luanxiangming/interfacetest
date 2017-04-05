package com.vipabc.interfacetest.backend.customerController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 09/03/2017.
 */
public class ActivatingAccountMsg {
    public static void activatingAccount(HttpFixture hf, String client, String checkid)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("url.server","url")+"/Customer/ActivatingAccount?client="+client+"&checkid="+checkid;
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
