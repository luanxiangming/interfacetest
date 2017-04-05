package com.vipabc.interfacetest.backend.myLessonsController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class PreparePageMsg {
    public static void preparePage(HttpFixture hf, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/MyLessons/PreparePage";

        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
