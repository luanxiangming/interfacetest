package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;

/**
 * Created by oliverluan on 30/03/2017.
 */
public class GetClientLessonHistoryMsg extends BaseMsg {
    public static void getClientLessonHistory(int brandId, int clientSn, int lessonType, String searchType, String beginTime, String endTime, int pageIdx, int pageCnt, boolean sessionTimeAsc, String attendance, String reviewType) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("information.server", "url") + "/Class/GetClientLessonHistory";
        String data = "{\"brandId\": " + brandId + ", \"clientSn\": " + clientSn + ", \"lessonType\": " + lessonType + ", \"searchType\": \"" + searchType + "\", \"beginTime\": \"" + beginTime + "\", \"endTime\": \"" + endTime + "\", \"pageIdx\": \"" + pageIdx + "\", \"pageCnt\": \"" + pageCnt + "\", \"sessionTimeAsc\": \"" + sessionTimeAsc + "\", \"attendance\": \"" + attendance + "\", \"reviewType\": \"" + reviewType + "\"}";
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
        htf.setRequestBody(data);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void getClientLessonHistory(int brandId, int clientSn, int lessonType, String searchType, String beginTime, String endTime) {
        getClientLessonHistory(brandId, clientSn, lessonType, searchType, beginTime, endTime, 1, 30, false, null, "all");
    }

}
