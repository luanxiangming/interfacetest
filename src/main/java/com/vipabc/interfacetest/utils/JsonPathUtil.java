package com.vipabc.interfacetest.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.jayway.jsonpath.JsonPath;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author echoshi
 *         JsonPath的解析
 */
public class JsonPathUtil {

    public JsonPathUtil() {

    }

    private static Logger logger = SystemLogger.getLogger(JsonPathUtil.class);

    /**
     * 功能说明：对Json的响应返回串，根据searchKey搜索得到Value
     *
     * @param jsonStr   被搜索的Json字符串
     * @param searchKey 要搜索的Key（JsonPath表达式） *
     * @param index     多个匹配时，取第几个 （索引从1开始）
     * @return String
     */
    public static String parseJsonPath(String jsonStr, String searchKey, int index) {
        String result = "";
        try {
            List<Object> list = JsonPath.read(jsonStr, searchKey);
            List<String> allResult = new ArrayList<String>();
            logger.debug("找到" + list.size() + "个");
            if (list.size() == 1) {
                logger.debug("根据搜素Key " + "\"" + searchKey + "\"" + "搜索到值是：" + list.get(0));
                return String.valueOf(list.get(index - 1));
            } else if (list.size() > 1 && list.size() != 0) {
                logger.debug("根据搜素Key " + "\"" + searchKey + "\"" + "搜索到值如下：");
                for (Object str : list) {
                    logger.debug("值为" + "\"" + str + "\"");
                    if (str instanceof Integer || str instanceof Float || str instanceof Double
                            || str instanceof Boolean || str instanceof String) {
                        allResult.add(String.valueOf(str));
                    }
                }
                return allResult.get(index - 1);
            }
        } catch (Exception e) {
            logger.info("未搜索到关键字匹配的内容，请确认关键字表达式是否正确");
        }
        return result;
    }

    public static Object getJsonValue(String jsonStr, String searchKey, int index) {
        List<Object> list = JsonPath.read(jsonStr, searchKey);
        if (list.size() > 0) {
            return list.get(index);
        } else {
            return "";
        }
    }

}
