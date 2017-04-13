package com.vipabc.interfacetest.backend.clientController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

/**
 * Created by oliverluan on 03/03/2017.
 */
public class GetClientBasicInfoByEmailMsg extends BaseMsg{
    public static String getClientInfoByEmail(String email)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("apistore.server","url")+"/VMD/VipJr.API/api/Client/GetClientBasicInfoByEmail";
        htf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"email\":\"" + email + "\"}";
        htf.setRequestBody(reqBody);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
        String JsonResult = htf.saveParamLeftstrRightstr("JsonResult\":\"", "\",\"");
        return JsonResult;

    }
}
