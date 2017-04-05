package com.vipabc.interfacetest.backend.classController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.DateUtils;
import com.vipabc.interfacetest.utils.Pro;

import java.util.Date;


/**
 * Created by tao on 27/03/2017.
 */
public class GetClientClassesMsg extends BaseMsg {

    /**
     * 取得顾客所有课程资讯
     *
     * @param brandId        品牌编号
     * @param clientSn       客户编号
     * @param classType      课程类型
     * @param searchType     搜索的课程类型
     * @param beginTime      开始时间
     * @param endTime        结束时间
     * @param pageIdx        页
     * @param pageCnt        每页数量
     * @param sessionTimeAsc 正反序
     */
    public static void getClientClasses(int brandId, int clientSn, String classType, int searchType, String beginTime, String endTime, int pageIdx, int pageCnt, String sessionTimeAsc) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("information.server", "url") + "/Class/GetClientClasses";
        String data = "{\"brandId\": \"" + brandId + "\", \"clientSn\": \"" + clientSn + "\", \"classType\": \"" + classType + "\", \"searchType\": " + searchType + ", \"beginTime\": \"" + beginTime + "\", \"endTime\": \"" + endTime + "\", \"pageIdx\": \"" + pageIdx + "\", \"pageCnt\": \"" + pageCnt + "\", \"sessionTimeAsc\": \"" + sessionTimeAsc + "\"}";
        htf.addHeaderValue("Content-Type", "application/json; charset=utf-8");
        htf.setRequestBody(data);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void getClientClasses(int brandId, int clientSn, String classType, int searchType, String beginTime, String endTime) {
        getClientClasses(brandId, clientSn, classType, searchType, beginTime, endTime, 1, 30, "true");
    }
}
