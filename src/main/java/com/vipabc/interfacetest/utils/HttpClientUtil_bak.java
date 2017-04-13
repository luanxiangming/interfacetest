package com.vipabc.interfacetest.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
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
import com.vipabc.interfacetest.common.DataTransfer;

/**
 * @author echoshi HttpClient工具类
 */
public class HttpClientUtil_bak {

	private static Logger logger = SystemLogger.getLogger(HttpClientUtil_bak.class);
	// 请求返回值
	private static String responseStr = "";
	// 登录后的cookie
	private static String cookieStr = "";

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
		Header[] headers = httpResponse.getHeaders("Set-Cookie");

		StringBuffer buf = new StringBuffer();
		for (Header header : headers) {
			buf.append(header.getValue()).append(";");
		}
		cookieStr = buf.toString();
		logger.info("Cookie String is :" + cookieStr);

		// 获取返回实体
		HttpEntity entity = httpResponse.getEntity();
		// 设置返回为UTF-8,并对中文Unicode码处理
		responseStr = Unicode2String.convertUnicode(EntityUtils.toString(entity, "UTF-8"));
		httpResponse.close();
		httpClients.close();
		return cookieStr;
	}
	

	/**
	 * Post请求，Body使用Json体
	 * 
	 * @param url
	 *            请求的URL
	 * @param header
	 *            请求头 (暂不使用)
	 * @param jsonBody
	 *            Post请求体
	 */
	public static void postMethodWithRawBody(String url, Map<String, String> header, String jsonBody)
			throws IOException {

		// 如果有"$#",执行替换参数操作
		if (jsonBody.indexOf("$#") != -1) {
			try {
				jsonBody = dealParamReplace(jsonBody);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 设置请求的实体
		StringEntity entity = new StringEntity(jsonBody);
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");

		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);

		// 这边暂不需报文头设置
		if (header != null && !header.isEmpty()) {
			for (Map.Entry<String, String> map : header.entrySet()) {
				httpPost.addHeader(map.getKey(), map.getValue());
			}
		}

//		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = httpClient.execute(httpPost);

		//200或者302都认为成功
		if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
			HttpEntity resEntity = response.getEntity();
			String resStr = EntityUtils.toString(resEntity, "UTF-8");

//			long endTime = System.currentTimeMillis();
//			long interval = endTime - startTime;
//			logger.info("执行请求：\"" + httpPost.getURI() + "\" 执行时间是" + interval + "豪秒");
//			if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
//				logger.error("请求:" + httpPost.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
//				Assertion.assertSetFlag(false);
//			}
			responseStr = Unicode2String.convertUnicode(resStr);
			logger.info("Response is:\n"+responseStr);
		}
		response.close();
		httpClient.close();
	}

	/** 
	 * Body使用表单形式	
	 */
	public static void postMethodWithForm(String url, Map<String, String> headerMap, Map<String, String> bodyMap)
			throws Exception {
		// TODO Auto-generated method stub
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// 添加请求头
		if (!headerMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		List<NameValuePair> pairsList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : bodyMap.entrySet()) {
			pairsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(pairsList, "UTF-8");
		httpPost.setEntity(reqEntity);
		
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = httpClient.execute(httpPost);
		//200或者302都认为成功
		if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
			HttpEntity resEntity = response.getEntity();
			String resStr = EntityUtils.toString(resEntity, "UTF-8");

//			long endTime = System.currentTimeMillis();
//			long interval = endTime - startTime;
//			logger.info("执行请求：\"" + httpPost.getURI() + "\" 执行时间是" + interval + "豪秒");
//			if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
//				logger.error("请求:" + httpPost.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
//				Assertion.assertSetFlag(false);
//			}
			responseStr = Unicode2String.convertUnicode(resStr);
			logger.info("Response is:\n"+responseStr);
		}
		response.close();
		httpClient.close();
	}

	/**
	 * @param url
	 *            Get请求的URL
	 * @param header
	 *            请求头 (暂不使用)
	 * @param pathParam LinkedHashMap保证参数按照顺序存放
	 *            get请求的路径参数
	 *            示例：http://192.168.235.31:8090/class/search?name=zhangsan&password=vipjr
	 */
	public static void getMethodWtihPathParam(String url, Map<String, String> header, LinkedHashMap<String, String> pathParam)
			throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		StringBuffer sb = new StringBuffer();
		int size = pathParam.size();
		int num = 1;
		if (pathParam != null && !pathParam.isEmpty()) {
			for (Map.Entry<String, String> map : pathParam.entrySet()) {
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
		logger.info("拼接后Get请求URL是："+reqUrl);
		HttpGet httpGet = new HttpGet(reqUrl);

		if (header != null && !header.isEmpty()) {
			for (Map.Entry<String, String> map : header.entrySet()) {
				httpGet.addHeader(map.getKey(), map.getValue());
			}
		}

//		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = httpClient.execute(httpGet);
//		 try {
//		 Thread.sleep(3000);
//		 } catch (InterruptedException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
		if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
			HttpEntity resEntity = response.getEntity();
			String resStr = EntityUtils.toString(resEntity, "UTF-8");
//			long endTime = System.currentTimeMillis();
//			long interval = endTime - startTime;
//			logger.info("执行请求：\"" + httpGet.getURI() + "\" 执行时间是" + interval + "豪秒");
//			if (interval > Long.valueOf(ProjectProperties.TIMEOUT) * 1000) {
//				logger.error("请求:" + httpGet.getURI() + " 超过" + ProjectProperties.TIMEOUT + "秒");
//				Assertion.assertSetFlag(false);
//			}
			responseStr = Unicode2String.convertUnicode(resStr);
			logger.info(responseStr);
		}
		response.close();
		httpClient.close();
	}

	public static String getResponse() {
		return responseStr;
	}
	
	
	/**
	 * 对Get请求的请求参数Encode处理
	 * 例："03/27/2017 15:23:28" 处理后为"03%2F27%2F2017%2015%3A23%3A28"	
	 */
	public static String getEncodingString(String str) throws Exception{
		return URLEncoder.encode(str, "UTF-8");		
	}
	
	

	/**
	 * 如有$#param#，执行替换操作
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String dealParamReplace(String jsonStr) throws Exception {
		Set<String> paramSet = getReplaceParam(jsonStr);
		for (String str2 : paramSet) {
			String value = DataTransfer.getData(str2);
			jsonStr = jsonStr.replaceAll("\\$#" + str2 + "#", value);
		}
		return jsonStr;
	}

	public static Set<String> getReplaceParam(String jsonStr) {
		Set<String> set = new HashSet<String>();
		int index = 0;
		int cur = 0;
		int begin = 0;
		String keyStr = "";
		while ((cur = jsonStr.indexOf("$#", index)) != -1) {
			index = cur + 2;// 跳过"${"两个字符
			if ((cur = jsonStr.indexOf("#", index)) != -1) {
				begin = index;
				keyStr = jsonStr.substring(begin, cur);
				set.add(keyStr.trim());
				index = cur + 1;
			}
		}
		return set;
	}
}
