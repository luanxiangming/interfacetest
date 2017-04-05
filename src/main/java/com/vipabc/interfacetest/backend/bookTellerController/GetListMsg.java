package com.vipabc.interfacetest.backend.bookTellerController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 27/03/2017.
 */
public class GetListMsg {
    public static void getList(HttpFixture hf, String requestPage, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/BookTeller/GetList";

        hf.setHeaderValue("Content-Type", "text/html; charset=utf-8");
        hf.addParamValue("requestPage", requestPage);
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();

    }
}
