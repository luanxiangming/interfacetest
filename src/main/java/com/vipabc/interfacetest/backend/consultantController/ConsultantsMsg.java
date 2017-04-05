package com.vipabc.interfacetest.backend.consultantController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 28/03/2017.
 */
public class ConsultantsMsg {
    public static void consultants(HttpFixture hf, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/Consultant/Consultants";

        hf.setHeaderValue("Content-Type", "text/html; charset=utf-8");
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();

    }
}
