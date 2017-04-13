package com.vipabc.interfacetest.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by echoshi on 2017/4/5.
 */
public class HttpClientUtil {

    public static Logger logger = SystemLogger.getLogger(HttpClientUtil.class);
    //返回的HttpResponse对象
    public static CloseableHttpResponse httpResponse;
    //返回的字符串
    public static String responseStr;

    /**
     * 获取登录后的Cookie
     */
    public static String getCookie(String loginUrl, String username, String password) throws Exception {

        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(loginUrl);
        List<NameValuePair> pairsList = new ArrayList<NameValuePair>();

        pairsList.add(new BasicNameValuePair("Account", username));
        pairsList.add(new BasicNameValuePair("Password", password));
        pairsList.add(new BasicNameValuePair("RememberMe", "false"));
        pairsList.add(new BasicNameValuePair("FromIms", "false"));

        UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(pairsList, "UTF-8");
        httpPost.setEntity(reqEntity);
        CloseableHttpResponse httpResponse = httpClients.execute(httpPost);

        httpResponse.getAllHeaders();
        org.apache.http.Header[] headers = httpResponse.getHeaders("Set-Cookie");

        StringBuffer buf = new StringBuffer();
        for (org.apache.http.Header header : headers) {
            buf.append(header.getValue()).append(";");
        }
        String cookieStr = buf.toString();
        logger.info("Cookie String is :" + cookieStr);

//        // 获取返回实体
//        HttpEntity entity = httpResponse.getEntity();
//        // 设置返回为UTF-8,并对中文Unicode码处理
//       responseStr = Unicode2String.convertUnicode(EntityUtils.toString(entity, "UTF-8"));
        httpResponse.close();
        httpClients.close();
        return cookieStr;
    }


    /**
     * Post请求，Body使用Json体
     * @param url       请求的URL
     * @param headerMap 请求头
     * @param jsonBody  Post请求体
     */
    public static String postMethodWithRawBody(String url, Map<String, String> headerMap, String jsonBody) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 设置请求的实体
        StringEntity entity = new StringEntity(jsonBody);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        // 报文头设置
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> map : headerMap.entrySet()) {
                httpPost.addHeader(map.getKey(), map.getValue());
            }
        }

        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 赋值httpResponse,用于处理返回报文头
        httpResponse = response;

        //200或者302都认为成功
        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
            HttpEntity resEntity = response.getEntity();
            String resStr = EntityUtils.toString(resEntity, "UTF-8");

            long endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            logger.info("执行请求：\"" + httpPost.getURI() + "\" 执行时间是" + interval + "豪秒");
            if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
                logger.error("请求:" + httpPost.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
                Assertion.assertSetFlag(false);
            }
            responseStr = Unicode2String.convertUnicode(resStr);
            logger.info("Response is:\n" + responseStr);
        }
        response.close();
        httpClient.close();
        return responseStr;
    }


    /**
     * Post请求，Body使用Json体     *
     *
     * @param url      请求的URL
     * @param jsonBody Post请求体
     */
    public static String postMethodWithRawBody(String url, String jsonBody)
            throws IOException {
        return postMethodWithRawBody(url, null, jsonBody);
    }


    /**
     * Body使用表单形式
     */
    public static String postMethodWithForm(String url, Map<String, String> headerMap, Map<String, String> bodyMap) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        // 添加请求头
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //处理表单
        List<NameValuePair> pairsList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : bodyMap.entrySet()) {
            pairsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(pairsList, "UTF-8");
        httpPost.setEntity(reqEntity);

        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 赋值httpResponse,用于处理返回报文头
        httpResponse = response;

        //200或者302都认为成功
        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
            HttpEntity resEntity = response.getEntity();
            String resStr = EntityUtils.toString(resEntity, "UTF-8");

            long endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            logger.info("执行请求：\"" + httpPost.getURI() + "\" 执行时间是" + interval + "豪秒");
            if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
                logger.error("请求:" + httpPost.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
                Assertion.assertSetFlag(false);
            }
            responseStr = Unicode2String.convertUnicode(resStr);
            logger.info("Response is:\n" + responseStr);
        }
        response.close();
        httpClient.close();
        return responseStr;
    }


    /**
     * Body使用表单形式
     */
    public static String postMethodWithForm(String url, Map<String, String> bodyMap) throws Exception {
        return postMethodWithForm(url, null, bodyMap);
    }


    /**
     * @param url                Get请求的URL
     * @param header             请求头
     * @param pathParamLinkedMap LinkedHashMap保证参数按照顺序存放
     *                           get请求的路径参数
     *                           示例：http://192.168.235.31:8090/class/search?name=zhangsan&password=vipjr
     */
    public static String getMethodWtihPathParam(String url, Map<String, String> header, LinkedHashMap<String, String> pathParamLinkedMap) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer sb = new StringBuffer();
        int size = pathParamLinkedMap.size();
        int num = 1;
        if (pathParamLinkedMap != null && !pathParamLinkedMap.isEmpty()) {
            for (Map.Entry<String, String> map : pathParamLinkedMap.entrySet()) {
                if (size == 1) {
                    sb.append(map.getKey()).append("=").append(map.getValue());
                    break;
                } else if (num < size) {
                    sb.append(map.getKey()).append("=").append(map.getValue()).append("&");
                    num++;
                } else if (num == size) {
                    sb.append(map.getKey()).append("=").append(map.getValue());
                }
            }
        }
        String reqUrl = url + "?" + sb.toString();
        logger.info("拼接后Get请求URL是：" + reqUrl);
        HttpGet httpGet = new HttpGet(reqUrl);

        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> map : header.entrySet()) {
                httpGet.addHeader(map.getKey(), map.getValue());
            }
        }

        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 赋值httpResponse,用于处理返回报文头
        httpResponse = response;
//		 try {
//		 Thread.sleep(3000);
//		 } catch (InterruptedException e)
//      {
//		 e.printStackTrace();
//		 }
        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
            HttpEntity resEntity = response.getEntity();
            String resStr = EntityUtils.toString(resEntity, "UTF-8");
            long endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            logger.info("执行请求：\"" + httpGet.getURI() + "\" 执行时间是" + interval + "豪秒");
            if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
                logger.error("请求:" + httpGet.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
                Assertion.assertSetFlag(false);
            }
            responseStr = Unicode2String.convertUnicode(resStr);
            logger.info(responseStr);
        }
        response.close();
        httpClient.close();
        return responseStr;
    }


    /**
     * @param url                Get请求的URL
     * @param pathParamLinkedMap LinkedHashMap保证参数按照顺序存放
     *                           get请求的路径参数
     *                           示例：http://192.168.235.31:8090/class/search?name=zhangsan&password=vipjr
     */
    public static String getMethodWtihPathParam(String url, LinkedHashMap<String, String> pathParamLinkedMap) throws IOException {
        return getMethodWtihPathParam(url, pathParamLinkedMap);
    }


    /**
     * 对Get请求的请求参数Encode处理
     * 例："03/27/2017 15:23:28" 处理后为"03%2F27%2F2017%2015%3A23%3A28"
     */
    public static String getEncodingString(String str) throws Exception {
        return URLEncoder.encode(str, "UTF-8");
    }
}
