package com.vipabc.interfacetest.backend.JRNewsController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetJRHotNewsListMsg {
    public static void getJRHotNewsList(HttpFixture hf)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("apistore.server","url")+"/VMD/VipJr.API/api/JRNews/GetJRHotNewsList";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
