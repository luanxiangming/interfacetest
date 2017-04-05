package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

/**
 * Created by oliverluan on 05/04/2017.
 */
public class GetClientClassRecordTimeListMsg extends BaseMsg {
    public static void getClientClassRecordTimeList(int brandId, int clientSn, String beginDate, String endDate) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("information.server", "url") + "/Class/GetClientClassRecordTimeList";
        String data = "{\"brandId\": " + brandId + ", \"clientSn\": " + clientSn + ", \"beginDate\": \"" + beginDate + "\", \"endDate\": \"" + endDate + "\"}";
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
        htf.setRequestBody(data);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

}
