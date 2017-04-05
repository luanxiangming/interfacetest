package shelper.iffixture;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;

import javax.net.ssl.SSLHandshakeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* http 工具类
 * 
*/
public class HttpFixture4 {
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String CHARSET_GBK = "GBK";
	private static final String SSL_DEFAULT_SCHEME = "https";
	private static final int SSL_DEFAULT_PORT = 443;
	protected String url = null;
	protected Map<String, String> paramlist = new HashMap();
	protected Map<String, String> headerlist = new HashMap();
	protected int status = 0;
	protected byte[] responseBody = null;
	protected String Body = "";
	protected Header[] headers = null;
	protected int fileno = 1;
	protected String requestbody = "";
	protected String encode = "utf-8";

    protected int requesttimeout = 120000;
	protected int connecttimeout = 20000;
	private boolean DoAuth = false;

	// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			// 设置恢复策略，在发生异常时候将自动重试3次
			if (executionCount >= 2) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};
	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		// 自定义响应处理
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_GBK : EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};
	/**
	* Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?search=p&name=s.....
	*
	* @param url
	* 提交地址
	* @return 响应消息
	*/
	public static String get(String url) {
		return get(url, null, null);
	}
	/**
	* Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
	*
	* @param url
	* 提交地址
	* @param params
	* 查询参数集, 键/值对
	* @return 响应消息
	*/
	public static String get(String url, Map<String, String> params) {
		return get(url, params, null);
	}
	/**
	* Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
	*
	* @param url
	* 提交地址
	* @param params
	* 查询参数集, 键/值对
	* @param charset
	* 参数提交编码集
	* @return 响应消息
	*/
	public static String get(String url, Map<String, String> params, String charset) {
		if (url == null || url.trim().equals("")) {
			return null;
		}
		List<NameValuePair> qparams = getParamsList(params);
		if (qparams != null && qparams.size() > 0) {
			charset = (charset == null ? CHARSET_GBK : charset);
			String formatParams = URLEncodedUtils.format(qparams, charset);
			url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url
					.substring(0, url.indexOf("?") + 1) + formatParams);
		}
        DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		HttpGet hg = new HttpGet(url);
		// 发送请求，得到响应
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hg, responseHandler);
		} catch (ClientProtocolException ex) {
			 Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "客户端连接协议错误",   ex);
		} catch (IOException ex) {
			Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "IO操作异常" ,  ex);
		} finally {
			abortConnection(hg, httpclient);
		}
		return responseStr;
	}
	/**
	* Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	*
	* @param url
	* 提交地址
	* @param params
	* 提交参数集, 键/值对
	* @return 响应消息
	*/
	public static String post(String url, Map<String, String> params) {
		return post(url, params, null);
	}
	/**
	* Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	*
	* @param url
	* 提交地址
	* @param params
	* 提交参数集, 键/值对
	* @param charset
	* 参数提交编码集
	* @return 响应消息
	*/
	public static String post(String url, Map<String, String> params, String charset) {
		if (url == null || url.trim().equals("")) {
			return null;
		}
		// 创建HttpClient实例
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		UrlEncodedFormEntity formEntity = null;
		try {
			if (charset == null || charset.trim().equals("")) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			}
		} catch (UnsupportedEncodingException e) {
			
		}
		HttpPost hp = new HttpPost(url);
		hp.setEntity(formEntity);
		// 发送请求，得到响应
		String responseStr = null;
		try {
            Reporter.log("post body -----:" + params.toString(), true);
            responseStr = httpclient.execute(hp, responseHandler);

        } catch (ClientProtocolException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "客户端连接协议错误" ,   ex);
		} catch (IOException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "IO操作异常",   ex);
		} finally {
			abortConnection(hp, httpclient);
		}
        Reporter.log("response-----:" + responseStr, true);
        return responseStr;
	}
	/**
	* Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证
	*
	* @param url
	* 提交地址
	* @param params
	* 提交参数集, 键/值对
	* @param charset
	* 参数编码集
	* @param keystoreUrl
	* 密钥存储库路径
	* @param keystorePassword
	* 密钥存储库访问密码
	* @param truststoreUrl
	* 信任存储库绝路径
	* @param truststorePassword
	* 信任存储库访问密码, 可为null
	* @return 响应消息
	* @throws NetServiceException
	*/
	public static String post(String url, Map<String, String> params, String charset, final URL keystoreUrl,
			final String keystorePassword, final URL truststoreUrl,	final String truststorePassword) {
		if (url == null || url.trim().equals("")) {
			return null;
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		UrlEncodedFormEntity formEntity = null;
		try {
			if (charset == null || charset.trim().equals("")) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			}
		} catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "不支持的编码集",   ex);
		}
		HttpPost hp = null;
		String responseStr = null;
		try {
			KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
			KeyStore trustStore = createKeyStore(truststoreUrl, truststorePassword);
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore,	keystorePassword, trustStore);
			Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, SSL_DEFAULT_PORT);
			httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
			hp = new HttpPost(url);
			hp.setEntity(formEntity);
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (NoSuchAlgorithmException ex) {
                         Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "指定的加密算法不可用" ,  ex);
		} catch (KeyStoreException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keytore解析异常",   ex);
		} catch (CertificateException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "信任证书过期或解析异常",   ex);
		} catch (FileNotFoundException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore文件不存在",   ex);
		} catch (IOException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "I/O操作失败或中断",   ex);
		} catch (UnrecoverableKeyException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore中的密钥无法恢复异常",   ex);
		} catch (KeyManagementException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "处理密钥管理的操作异常",   ex);
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
	/**
	* Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证
	* @param url
	* 提交地址
	* @param str
	* post请求体的内容
	* @param charset
	* 参数编码集
	* @param keystoreUrl
	* 密钥存储库路径
	* @param keystorePassword
	* 密钥存储库访问密码
	* @param truststoreUrl
	* 信任存储库绝路径
	* @param truststorePassword
	* 信任存储库访问密码, 可为null
	* @return 响应消息
	* @throws NetServiceException
	*/
	public static String post(String url, String str, String charset, final URL keystoreUrl,
			final String keystorePassword, final URL truststoreUrl,	final String truststorePassword) {
		if (url == null || url.trim().equals("")) {
			return null;
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		StringEntity stringentity = null;
		try {
			if (charset == null || charset.trim().equals("")) {
				stringentity = new StringEntity(URLEncoder.encode(str));
			} else {
				stringentity = new StringEntity(URLEncoder.encode(str,charset));
			}
		} catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "不支持的编码集",   ex);
		}
		HttpPost hp = null;
		String responseStr = null;
		try {
			KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
			KeyStore trustStore = createKeyStore(truststoreUrl, truststorePassword);
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore,	keystorePassword, trustStore);
			Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, SSL_DEFAULT_PORT);
			httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
			hp = new HttpPost(url);
			hp.setEntity(stringentity);
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (NoSuchAlgorithmException ex) {
                         Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "指定的加密算法不可用" ,  ex);
		} catch (KeyStoreException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keytore解析异常",   ex);
		} catch (CertificateException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "信任证书过期或解析异常",   ex);
		} catch (FileNotFoundException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore文件不存在",   ex);
		} catch (IOException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "I/O操作失败或中断",   ex);
		} catch (UnrecoverableKeyException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore中的密钥无法恢复异常" ,  ex);
		} catch (KeyManagementException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "处理密钥管理的操作异常",   ex);
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
	/**
	* Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证,http Authentication
	*
	* @param url
	* 提交地址
	* @param params
	* 提交参数集, 键/值对
	* @param charset
	* 参数编码集
	* @param keystoreUrl
	* 密钥存储库路径
	* @param keystorePassword
	* 密钥存储库访问密码
	* @param truststoreUrl
	* 信任存储库绝路径
	* @param truststorePassword
	* 信任存储库访问密码, 可为null
       * @param authip
	* 授权ip
      * @param authport
	* 授权端口
       * @param usernamecredentials
	* 鉴权用户名
      * @param usernamepassword
	* 鉴权密码
        * @param sslport
	* @return 响应消息
	*/
	public static String post(String url, Map<String, String> params, String charset, final URL keystoreUrl,
			final String keystorePassword, final URL truststoreUrl,	final String truststorePassword,String authip,int autport,String usernamecredentials,String usernamepassword,int sslport) {
                            HttpHost targetHost = new HttpHost(authip,autport);
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
                httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(usernamecredentials, usernamepassword));
		if (url == null || url.trim().equals("")) {
			return null;
		}
		UrlEncodedFormEntity formEntity = null;
		try {
			if (charset == null || charset.trim().equals("")) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			}
		} catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "不支持的编码集",   ex);
		}
		HttpPost hp = null;
		String responseStr = null;
		try {
			KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
			KeyStore trustStore = createKeyStore(truststoreUrl, truststorePassword);
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore,	keystorePassword, trustStore);
			Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, sslport);
			httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
			hp = new HttpPost(url);
			hp.setEntity(formEntity);
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (NoSuchAlgorithmException ex) {
                         Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "指定的加密算法不可用" ,  ex);
		} catch (KeyStoreException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keytore解析异常" ,  ex);
		} catch (CertificateException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE,  "信任证书过期或解析异常",  ex);
		} catch (FileNotFoundException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore文件不存在" ,  ex);
		} catch (IOException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "I/O操作失败或中断",   ex);
		} catch (UnrecoverableKeyException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore中的密钥无法恢复异常",  ex);
		} catch (KeyManagementException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "处理密钥管理的操作异常",   ex);
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
	/**
	* Post方式提交,忽略URL中包含的参数,解决SSL双向数字证书认证, http Authentication
	* @param url
	* 提交地址
	* @param str
	* post请求体的内容
	* @param charset
	* 参数编码集
	* @param keystoreUrl
	* 密钥存储库路径
	* @param keystorePassword
	* 密钥存储库访问密码
	* @param truststoreUrl
	* 信任存储库绝路径
	* @param truststorePassword
	* 信任存储库访问密码, 可为null
       * @param authip
	* 授权ip
      * @param authport
	* 授权端口
       * @param usernamecredentials
	* 鉴权用户名
        * @param sslport
      * @param usernamepassword
	* 鉴权密码
	* @return 响应消息
	* @throws NetServiceException
	*/
public static String post(String url, String str, String charset, final URL keystoreUrl,
			final String keystorePassword, final URL truststoreUrl,	final String truststorePassword,String authip,int autport,String usernamecredentials,String usernamepassword,int sslport) {
                HttpHost targetHost = new HttpHost(authip,autport);
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
                httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(usernamecredentials, usernamepassword));
		if (url == null || url.trim().equals("")) {
			return null;
		}

		StringEntity stringentity = null;
		try {
			if (charset == null || charset.trim().equals("")) {
				stringentity = new StringEntity(URLEncoder.encode(str));
			} else {
				stringentity = new StringEntity(URLEncoder.encode(str,charset));
			}
		} catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "不支持的编码集" ,  ex);
		}
		HttpPost hp = null;
		String responseStr = null;
		try {
			KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
			KeyStore trustStore = createKeyStore(truststoreUrl, truststorePassword);
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore,	keystorePassword, trustStore);
			Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, sslport);
			httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
			hp = new HttpPost(url);
			hp.setEntity(stringentity);
                        
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (NoSuchAlgorithmException ex) {
                         Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "指定的加密算法不可用" ,  ex);
		} catch (KeyStoreException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keytore解析异常" ,  ex);
		} catch (CertificateException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "信任证书过期或解析异常" ,  ex);
		} catch (FileNotFoundException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore文件不存在",   ex);
		} catch (IOException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "I/O操作失败或中断",   ex);
		} catch (UnrecoverableKeyException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "keystore中的密钥无法恢复异常",   ex);
		} catch (KeyManagementException ex) {
                        Logger.getLogger(HttpFixture4.class.getName()).log(Level.SEVERE, "处理密钥管理的操作异常",   ex);
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
}


	/**
	* 获取DefaultHttpClient实例
	*
	* @param charset
	* 参数编码集, 可空
	* @return DefaultHttpClient 对象
	*/
	private static DefaultHttpClient getDefaultHttpClient(final String charset){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset == null ? CHARSET_GBK : charset);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}

	/**
	* 释放HttpClient连接
	*
	* @param hrb
	* 请求对象
	* @param httpclient
	* 			client对象
	*/
	private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient){
		if (hrb != null) {
			hrb.abort();
		}
		if (httpclient != null) {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	* 从给定的路径中加载此 KeyStore
	*
	* @param url
	* keystore URL路径
	* @param password
	* keystore访问密钥
	* @return keystore 对象
	*/
	private static KeyStore createKeyStore(final URL url, final String password)
			throws KeyStoreException, NoSuchAlgorithmException,	CertificateException, IOException {
		if (url == null) {
			throw new IllegalArgumentException("Keystore url may not be null");
		}
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream is = null;
		try {
			is = url.openStream();
			keystore.load(is, password != null ? password.toCharArray() : null);
		} finally {
			if (is != null){
				is.close();
				is = null;
			}
		}
		return keystore;
	}

	/**
	* 将传入的键/值对参数转换为NameValuePair参数集
	*
	* @param paramsMap
	* 参数集, 键/值对
	* @return NameValuePair参数集
	*/
	private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}

    public void nextRequest() {
        url = null;
        paramlist = new HashMap();
        status = 0;
        responseBody = null;
        Body = "";
        headers = null;
        requestbody = "";
    }

    public int getStatus() {
        return this.status;
    }

    /**
     * 检查reponse是否包含参数的字符串
     */
    public boolean findStringinResponse(String content) {
        if (content.equals("MYHTTPCLIENT_ZERORESPONSE")) {
            return this.responseBody.length == 0;
        } else {

            byte[] des;
            try {
                des = content.getBytes(encode);
                for (int a = 0; a < (responseBody.length - des.length + 1); a++) {
                    boolean result = true;
                    for (int b = 0; b < des.length; b++) {
                        if (responseBody[a + b] != des[b]) {
                            result = false;
                            break;
                        }
                    }
                    if (result) {
                        Reporter.log("检查：response中查找字符串:" + content
                                + "----true", true);
                        return true;
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Reporter.log(ex.getMessage(), true);
            }
            Reporter.log("检查：response中查找字符串:" + content + "----false", true);
            return false;
        }
    }

    public void addHeaderValue(String headername, String headervalue) {
        headerlist.put(headername, headervalue);
    }

    public String getHeaderValue(String headername) {
        return headerlist.get(headername);
    }

    /**
     * 添加请求体
     */
    public void addRequestBody(String reqbody) {
        this.requestbody += reqbody;
    }

    public void setRequestBody(String reqbody) {
        this.requestbody = reqbody;
    }

    public String getResponseheader(String headername) {
        for (Header header : headers) {
            if (header.getName().equals(headername)) {
                return header.getValue();
            }
        }
        return "";
    }

    public String getResponseheaders() {
        String headerstr = "";
        for (Header header : headers) {
            headerstr = headerstr + header.getName() + "=" + header.getValue()
                    + ";";
        }
        return headerstr;
    }

    /**
     * 设置请求的url
     * @throws UnsupportedEncodingException
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 通过左右边界抓出目标字符串返回
     */
    public String saveParamLeftstrRightstr(String leftstr, String rightstr) {
        byte[] left;
        byte[] right;
        byte[] content = null;
        int start;
        int end;
        try {

            left = isContainChinese(leftstr)? leftstr.getBytes("GBK"):leftstr.getBytes(encode);
            right = isContainChinese(rightstr)? rightstr.getBytes("GBK"):rightstr.getBytes(encode);

            for (int a = 0; a < (responseBody.length - left.length
                    - right.length + 1); a++) {
                boolean result = true;
                for (int b = 0; b < left.length; b++) {
                    if (responseBody[a + b] != left[b]) {
                        result = false;
                        break;
                    }
                }

                if (result) {
                    // 注意
                    start = a + left.length;
                    for (int a1 = start; a1 < (responseBody.length
                            - right.length + 1); a1++) {
                        boolean result2 = true;
                        for (int b1 = 0; b1 < right.length; b1++) {
                            if (responseBody[a1 + b1] != right[b1]) {
                                result2 = false;
                                break;
                            }
                        }
                        if (result2) {
                            end = a1 - 1;
                            if (start > end) {
                                // System.out.println("start is "+start);
                                // System.out.println("end is "+end);
                                // System.out.println("start>end");
                                Reporter.log("关联：在response中通过左边界：" + leftstr
                                        + "。右边界：" + rightstr + "关联到的内容为空", true);
                                return "";
                            } else {
                                content = new byte[end - start + 1];
                                int j = 0;
                                for (int a2 = start; a2 <= end; a2++) {
                                    content[j] = responseBody[a2];
                                    j++;
                                }
                                String collstr = new String(content, encode);
                                Reporter.log("关联：在response中通过左边界：" + leftstr
                                        + "。右边界：" + rightstr + "关联到的内容为:"
                                        + collstr, true);
                                return collstr;
                            }
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Reporter.log(ex.getMessage(), true);
        }
        return "";
    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public String getResponseBody() {
        return this.Body;
    }

    public HttpFixture4(String username, String password, String workstation, String domain) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new NTCredentials(username, password, workstation, domain));

        HttpHost target = new HttpHost("cpims.vipabc.com", 80, "http");

        // Make sure the same context is used to execute logically related requests
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);

        DoAuth = true;

    }
}