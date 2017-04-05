package com.vipabc.interfacetest.backend.videoController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 23/03/2017.
 */
public class RecommandListMsg {
    public static void recommandList(HttpFixture hf, String type, String page, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/Video/RecommandList";

        hf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        hf.addHeaderValue("X-Requested-With", "XMLHttpRequest");

        hf.addCookieValue("Cookie", cookies);
        hf.addParamValue("type", type);
        hf.addParamValue("page", page);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
