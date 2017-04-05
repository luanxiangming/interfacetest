package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class GetClientReservedLessonsMsg extends BaseMsg{
    public static void getClientReservedLessons(int brandId, int clientSn, int lessonType, String beginTime, String endTime, int pageIdx, int pageCnt, boolean sessionTimeAsc) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("information.server", "url") + "/Class/GetClientReservedLessons";
        String data = "{\"brandId\": " + brandId + ", \"clientSn\": " + clientSn + ", \"lessonType\": " + lessonType + ", \"beginTime\": \"" + beginTime + "\", \"endTime\": \"" + endTime + "\", \"pageIdx\": " + pageIdx + ", \"pageCnt\": " + pageCnt + ", \"sessionTimeAsc\": " + sessionTimeAsc + "}";
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
        htf.setRequestBody(data);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void getClientReservedLessons(int brandId, int clientSn, int lessonType, String beginTime, String endTime) {
        getClientReservedLessons(brandId, clientSn, lessonType, beginTime, endTime, 1, 20, true);
    }

}
