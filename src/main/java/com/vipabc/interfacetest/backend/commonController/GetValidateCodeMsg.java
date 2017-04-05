package com.vipabc.interfacetest.backend.commonController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetValidateCodeMsg {
    public static void getValidateCode(HttpFixture hf, String t)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("url.server","url")+"/Common/GetValidateCode?t="+t;
        hf.setHeaderValue("Content-Type", "image/jpeg");

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();
    }
}
