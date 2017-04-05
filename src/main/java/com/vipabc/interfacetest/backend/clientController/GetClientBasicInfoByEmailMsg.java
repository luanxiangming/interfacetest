package com.vipabc.interfacetest.backend.clientController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetClientBasicInfoByEmailMsg {
    public static String getClientInfoByEmail(HttpFixture hf, String email)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("apistore.server","url")+"/VMD/VipJr.API/api/Client/GetClientBasicInfoByEmail";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"email\":\"" + email + "\"}";
        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
        String JsonResult = hf.saveParamLeftstrRightstr("JsonResult\":\"", "\",\"");
        return JsonResult;

    }
}
