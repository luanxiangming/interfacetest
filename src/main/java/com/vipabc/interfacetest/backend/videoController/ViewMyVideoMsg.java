package com.vipabc.interfacetest.backend.videoController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 20/03/2017.
 */
public class ViewMyVideoMsg {
    public static void viewMyVideo(HttpFixture hf, String sessionSn, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/Video/ViewMyVideo";

        hf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        hf.addCookieValue("Cookie", cookies);
        hf.addParamValue("sessionSn", sessionSn);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
