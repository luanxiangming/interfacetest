package com.vipabc.interfacetest.backend.accountController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 24/03/2017.
 */
public class ClassContractMsg {
    public static void classContract(HttpFixture hf, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/Account/ClassContract";

        hf.setHeaderValue("Content-Type", "text/html; charset=utf-8");
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();

    }
}
