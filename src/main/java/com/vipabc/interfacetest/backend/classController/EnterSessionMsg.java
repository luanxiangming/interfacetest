package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

/**
 * Created by oliverluan on 12/04/2017.
 */
public class EnterSessionMsg extends BaseMsg {
    public static void enterSession(int brandId, int clientSn, Boolean isQuickSession, String sessionSn) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("information.server", "url") + "/Class/EnterSession";
        String data = "{\"brandId\": \"" + brandId + "\", \"clientSn\": \"" + clientSn + "\", \"sessionSn\": \"" + sessionSn + "\", \"isQuickSession\": " + isQuickSession + "}";
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
        htf.setRequestBody(data);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

}
