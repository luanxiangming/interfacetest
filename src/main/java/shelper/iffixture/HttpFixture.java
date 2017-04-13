package shelper.iffixture;

import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.params.CookiePolicy;
import org.testng.Reporter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter.sun
 */
public class HttpFixture {
    protected String url = null;
    protected Map<String, String> paramlist = new HashMap();
    protected Map<String, String> headerlist = new HashMap();
    protected int status = 0;
    protected byte[] responseBody = null;
    public String Body = "";
    protected Header[] headers = null;
    protected int fileno = 1;
    protected String requestbody = "";
    protected String cookie = "";
    protected CookieStore cookieStore;

    protected String encode = "utf-8";
    HttpClient client = null;
    protected int requesttimeout = 120000;
    protected int connecttimeout = 20000;
    private boolean DoAuth = false;

    private String boundary = "";
    private String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    public void setRequesttimeout(int time) {
        this.requesttimeout = time;
    }

    public void setConnecttimeout(int time) {
        this.connecttimeout = time;
    }

    /**
     * 获得请求返回的code
     */
    public int getStatus() {
        return this.status;
    }

    public String getResponseBody() { return this.Body; }

    public void nextRequest() {
        url = null;
        paramlist = new HashMap();
        headerlist = new HashMap();
        status = 0;
        responseBody = null;
        Body = "";
        headers = null;
        requestbody = "";
        cookie = "";
    }

    public void clearDefinedheaders() {
        headerlist = new HashMap();
    }

    public HttpFixture() {
        client = new HttpClient();
    }

	/*
     * 用户名密码鉴权
	 */
//	public HttpFixture(String authip, int autport, String creuser,
//			String crepass) {
//		client = new HttpClient();
//		client.getState().setCredentials(new AuthScope(authip, autport),
//				new UsernamePasswordCredentials(creuser, crepass));
//		DoAuth = true;
//	}

    public HttpFixture(String username, String password, String workstation, String domain) {
        client = new HttpClient();
        client.getState().setCredentials(AuthScope.ANY,
                new NTCredentials(username, password, workstation, domain));
        DoAuth = true;

    }

    /**
     * 设置请求的url
     *
     * @throws UnsupportedEncodingException
     */
    public void setUrl(String url) {
        this.url = url;
    }

	/*
     * 双向证书鉴权
	 */
/*	public HttpFixture(String authip, int autport, String creuser,
            String crepass, URL keystore, String keystorepass,
			URL trustkeystore, String trustkeystorepass) {
		client = new HttpClient();
		client.getParams().setAuthenticationPreemptive(true);
		client.getState().setCredentials(new AuthScope(authip, autport),
				new UsernamePasswordCredentials(creuser, crepass));
		Protocol authhttps = new Protocol("https",
				new AuthSSLProtocolSocketFactory(keystore, keystorepass,
						trustkeystore, trustkeystorepass), autport);
		Protocol.registerProtocol("https", authhttps);
		DoAuth = true;

	}*/

    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 添加请求参数的键值对
     */
    public void addParamValue(String paramname, String value) {
        try {
            // paramlist.put(paramname, value);
            if (paramname.length() != paramname.getBytes().length
                    && value.length() != value.getBytes().length) {
                this.requestbody += URLEncoder.encode(paramname, this.encode)
                        + "=" + URLEncoder.encode(value, this.encode) + "&";
            }
            if (paramname.length() == paramname.getBytes().length
                    && value.length() != value.getBytes().length) {
                this.requestbody += paramname + "="
                        + URLEncoder.encode(value, this.encode) + "&";
            }
            if (paramname.length() != paramname.getBytes().length
                    && value.length() == value.getBytes().length) {
                this.requestbody += URLEncoder.encode(paramname, this.encode)
                        + "=" + value + "&";
            }
            if (paramname.length() == paramname.getBytes().length
                    && value.length() == value.getBytes().length) {
                this.requestbody += paramname + "=" + value + "&";
            }
        } catch (UnsupportedEncodingException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }

    /**
     * 添加接口请求参数的键值对
     */
    public void addUrlParamValue(String paramname, String value) {
        try {
            this.url += "&";
            // paramlist.put(paramname, value);
            if (paramname.length() != paramname.getBytes().length
                    && value.length() != value.getBytes().length) {
                this.url += URLEncoder.encode(paramname, this.encode)
                        + "=" + URLEncoder.encode(value, this.encode) + "&";
            }
            if (paramname.length() == paramname.getBytes().length
                    && value.length() != value.getBytes().length) {
                this.url += paramname + "="
                        + URLEncoder.encode(value, this.encode) + "&";
            }
            if (paramname.length() != paramname.getBytes().length
                    && value.length() == value.getBytes().length) {
                this.url += URLEncoder.encode(paramname, this.encode)
                        + "=" + value + "&";
            }
            if (paramname.length() == paramname.getBytes().length
                    && value.length() == value.getBytes().length) {
                this.url += paramname + "=" + value + "&";
            }
        } catch (UnsupportedEncodingException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }


    public void addHeaderValue(String headername, String headervalue) {
        headerlist.put(headername, headervalue);
    }

    public void setHeaderValue(String headername, String headervalue) {
        headerlist.clear();
        headerlist.put(headername, headervalue);
    }


    public void addCookieValue(String cookiename, String cookievalue) {
        headerlist.put(cookiename, cookievalue);
    }

    public void quit() {
        this.cookie = "";
        this.client = new HttpClient();
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

    public void setCookies(String cookie) {
        this.cookie = cookie;
    }


    public String getResponseheader(String headername) {
        for (Header header : headers) {
            if (header.getName().equals(headername)) {
                return header.getValue();
            }
        }
        return "";
    }

    public String getResponseheaders(String headername) {
        String headerstr = "";
        for (Header header : headers) {
            if (header.getName().equals(headername)) {
                headerstr = headerstr + header.getName() + "=" + header.getValue()
                        + ";";
            }
        }
        return headerstr;
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
     * 发get
     */
    public void Get() {
        String paramstring;
        if (!this.url.contains("?")) {
            paramstring = this.url + "?";
        } else {
            paramstring = this.url + "&";
        }
        paramstring += this.requestbody;
        GetMethod method = new GetMethod(paramstring);
        for (Cookie c : client.getState().getCookies()) {
            cookie += c.toString() + ";";
        }
        method.setRequestHeader("Cookie", cookie);
        if (DoAuth) {
            method.setDoAuthentication(DoAuth);
        }
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
                requesttimeout);
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(connecttimeout);
        Iterator iter1 = headerlist.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            try {
                method.addRequestHeader((String) entry.getKey(),
                        (String) entry.getValue());
            } catch (Exception ex) {
                Reporter.log(ex.getMessage(), true);
            }
        }

        try {
            Reporter.log("get URI----:" + method.getURI(), true);
            status = client.executeMethod(method);
            if (status != HttpStatus.SC_OK) {
                Reporter.log("Method failed: " + method.getStatusLine(), true);
            }
            // responseBody = method.getResponseBody();
            responseBody = IOUtils
                    .toByteArray(method.getResponseBodyAsStream());
            headers = method.getResponseHeaders();

            // Body=responseBody.toString();
            Body = new String(responseBody, encode);
            Reporter.log("response -----:" + Body, true);
        } catch (HttpException e) {
            Reporter.log("Fatal protocol violation: " + e.getMessage(), true);
        } catch (IOException e) {
            Reporter.log("Fatal transport error: " + e.getMessage(), true);
        } finally {
            method.releaseConnection();
        }
    }

    /**
     * 发post
     */

    public String Post() {
        PostMethod method = new PostMethod(url);
        if (DoAuth) {
            method.setDoAuthentication(DoAuth);
        }
        Iterator iter1 = headerlist.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            try {
                method.addRequestHeader((String) entry.getKey(),
                        (String) entry.getValue());
            } catch (Exception ex) {
                Reporter.log(ex.getMessage(), true);
            }
        }
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
                requesttimeout);
        method.getParams().setParameter(
                "http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(connecttimeout);

        // method.setRequestBody(this.requestbody);
        // 用StringRequestEntity代替setRequestBody
        try {
            String ct = "";
            if (this.getHeaderValue("contentType") != null) {
                ct = this.getHeaderValue("contentType");
            }
            StringRequestEntity a1 = new StringRequestEntity(this.requestbody,
                    ct, this.encode);
            method.setRequestEntity(a1);
            for (Cookie c : client.getState().getCookies()) {
                cookie += c.toString() + ";";
            }
            method.setRequestHeader("Cookie", cookie);
        } catch (UnsupportedEncodingException ex) {
            Reporter.log(ex.getMessage(), true);
        }
        try {
            Reporter.log("post url -----:" + this.url, true);
            Reporter.log("post header -----:" + this.headerlist, true);
            Reporter.log("post body -----:" + this.requestbody, true);
            Reporter.log("post cookie -----:" + this.cookie, true);
            status = client.executeMethod(method);
            if (status != HttpStatus.SC_OK) {
                Reporter.log("Method failed: " + method.getStatusLine(), true);
            }
            // responseBody = method.getResponseBody();
            responseBody = IOUtils
                    .toByteArray(method.getResponseBodyAsStream());
            // Body=responseBody.toString();
            Body = new String(responseBody, encode);
            headers = method.getResponseHeaders();
            Reporter.log("response-----:" + Body, true);
        } catch (HttpException e) {
            Reporter.log("Fatal protocol violation: " + e.getMessage(), true);
        } catch (IOException e) {
            Reporter.log("Fatal transport error: " + e.getMessage(), true);
        } finally {
            method.releaseConnection();
        }
        return Body;
    }

    public String Put() {
//		PostMethod method = new PostMethod(url);
        PutMethod method = new PutMethod(url);
        if (DoAuth) {
            method.setDoAuthentication(DoAuth);
        }
        Iterator iter1 = headerlist.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry entry = (Map.Entry) iter1.next();
            try {
                method.addRequestHeader((String) entry.getKey(),
                        (String) entry.getValue());
            } catch (Exception ex) {
                Reporter.log(ex.getMessage(), true);
            }
        }
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
                requesttimeout);
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(connecttimeout);

        // method.setRequestBody(this.requestbody);
        // 用StringRequestEntity代替setRequestBody
        try {
            String ct = "";
            if (this.getHeaderValue("contentType") != null) {
                ct = this.getHeaderValue("contentType");
            }
            StringRequestEntity a1 = new StringRequestEntity(this.requestbody,
                    ct, this.encode);
            method.setRequestEntity(a1);
        } catch (UnsupportedEncodingException ex) {
            Reporter.log(ex.getMessage(), true);
        }
        try {
            Reporter.log("post body -----:" + this.requestbody, true);
            status = client.executeMethod(method);
            if (status != HttpStatus.SC_OK) {
                Reporter.log("Method failed: " + method.getStatusLine(), true);
            }
            // responseBody = method.getResponseBody();
            responseBody = IOUtils
                    .toByteArray(method.getResponseBodyAsStream());
            headers = method.getResponseHeaders();
            // Body=responseBody.toString();
            Body = new String(responseBody, encode);
            Reporter.log("response-----:" + Body, true);
        } catch (HttpException e) {
            Reporter.log("Fatal protocol violation: " + e.getMessage(), true);
        } catch (IOException e) {
            Reporter.log("Fatal transport error: " + e.getMessage(), true);
        } finally {
            method.releaseConnection();
        }
        return Body;
    }

    public String Delete() {
        return Body;
    }

    public boolean saveResponse2File(String filename)
            throws FileNotFoundException {
        Date dateNow = new Date();
        String[] filepath = filename.split("\\\\");
        String filep = "";
        for (int i = 0; i < filepath.length - 1; i++) {
            filep += filepath[i] + "\\";
        }
        File path = new File(filep);
        if (!path.isDirectory()) {
            path.mkdir();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy年MM月dd日HH时mm分ss秒");
        String dateNowStr = dateFormat.format(dateNow);
        FileOutputStream fis = new FileOutputStream(filename + dateNowStr
                + fileno + ".html", true);
        fileno++;
        try {
            fis.write(responseBody);
        } catch (Exception ex) {
            Reporter.log(ex.getMessage(), true);
            return false;
        }
        try {
            fis.close();
        } catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
            return false;
        }
        // System.out.println(Body);
        return true;
    }

    /**
     * 检查reponse中是否包含指定文件中的内容。
     */
    public boolean findFileStringinResponse(String filename) {
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(filename);
            if (filename == null || filename.equals("")) {
                throw new NullPointerException("无效的文件路径");
            }
            long len = file.length();
            byte[] bytes = new byte[(int) len];
            bufferedInputStream = new BufferedInputStream(new FileInputStream(
                    file));
            int r = bufferedInputStream.read(bytes);
            if (r != len) {
                throw new IOException("读取文件不正确");
            }
            bufferedInputStream.close();
            String content = new String(bytes, encode);

            if (content.equals("MYHTTPCLIENT_ZERORESPONSE")) {
                boolean kongresult = (this.responseBody.length == 0);
                Reporter.log("检查：reponse为空----" + kongresult, true);
                return this.responseBody.length == 0;
            } else {

                byte[] des;
                try {

                    des = bytes;
                    for (int a = 0; a < (responseBody.length - des.length + 1); a++) {
                        boolean result = true;
                        for (int b = 0; b < des.length; b++) {
                            if (responseBody[a + b] != des[b]) {
                                result = false;
                                break;
                            }
                        }
                        if (result) {
                            Reporter.log("检查：reponse包含" + filename
                                    + "中的内容----true", true);
                            return true;
                        }
                    }
                } catch (Exception ex) {
                    Reporter.log(ex.getMessage(), true);
                }
                Reporter.log("检查：reponse包含" + filename + "中的内容----false", true);
                return false;
            }
        } catch (FileNotFoundException ex) {
            Reporter.log(ex.getMessage(), true);
            Reporter.log("检查：reponse包含" + filename + "中的内容----false", true);
            return false;
        } catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
            Reporter.log("检查：reponse包含" + filename + "中的内容----false", true);
            return false;
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException ex) {
                Reporter.log("检查：reponse包含" + filename + "中的内容----false", true);
                return false;
            }
        }

    }

    /**
     * 检查reponse是否同指定的文件中所包含的正则表达式匹配
     */
    public boolean FileMatchResponse(String filename) {
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(filename);
            if (filename == null || filename.equals("")) {
                throw new NullPointerException("无效的文件路径");
            }
            long len = file.length();
            byte[] bytes = new byte[(int) len];
            bufferedInputStream = new BufferedInputStream(new FileInputStream(
                    file));
            int r = bufferedInputStream.read(bytes);
            if (r != len) {
                throw new IOException("读取文件不正确");
            }
            bufferedInputStream.close();
            String content = new String(bytes, encode);

            if (content.equals("MYHTTPCLIENT_ZERORESPONSE")) {
                return this.responseBody.length == 0;
            } else {
                String responseaim = new String(responseBody, encode);
                boolean matchresult = responseaim.matches(content);
                Reporter.log("检查：reponse匹配" + filename + "中的正则表达----"
                        + matchresult, true);
                return matchresult;
            }
        } catch (FileNotFoundException ex) {
            Reporter.log(ex.getMessage(), true);
            Reporter.log("检查：reponse匹配" + filename + "中的正则表达----false", true);
            return false;
        } catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
            Reporter.log("检查：reponse匹配" + filename + "中的正则表达----false", true);
            return false;
        } finally {
            try {
                bufferedInputStream.close();
            } catch (IOException ex) {
                Reporter.log("检查：reponse匹配" + filename + "中的正则表达----false",
                        true);
                return false;
            }
        }

    }

    /**
     * 检查reponse是否同参数的正则表达式匹配
     */
    public boolean ResponseMatch(String content) {
        if (content.equals("MYHTTPCLIENT_ZERORESPONSE")) {
            boolean kongresult = (this.responseBody.length == 0);
            Reporter.log("检查：reponse为空----" + kongresult, true);
            return kongresult;
        } else {
            try {
                String responseaim = new String(responseBody, encode);
                boolean result = responseaim.matches(content);
                Reporter.log("检查：ResponseMatch:" + content + "----" + result,
                        true);
                return result;
            } catch (Exception ex) {
                Reporter.log(ex.getMessage(), true);
                return false;
            }
        }
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

    /**
     * 返回参数在reponse中出现的次数
     */
    public int findNumberofStringinResponse(String content) {
        if (content != null && !content.equals("")) {
            int count = 0;
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
                        count++;
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Reporter.log(ex.getMessage(), true);
            }
            Reporter.log("统计：reponse出现： " + content + " 的次数为" + count, true);
            return count;
        } else {
            Reporter.log("统计：reponse出现： " + content + " 的次数为0", true);
            return 0;
        }

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

            left = isContainChinese(leftstr) ? leftstr.getBytes("GBK") : leftstr.getBytes(encode);
            right = isContainChinese(rightstr) ? rightstr.getBytes("GBK") : rightstr.getBytes(encode);

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

    /**
     * 默认从body中提取，也可以自定义内容提取，可以参数key|key,index|body,key|body,key,indx
     * @param params
     * @return
     */
    public Object getValue(Object... params) {
        String str = this.Body;
        int index = 0;
        if (params.length == 0) {
            return str;
        }
        String key = (String) params[0];
        if (params.length == 2) {
            if (params[1] instanceof String) {
                key = (String) params[1];
            } else {
                index = (Integer) params[1];
            }
        }
        if (params.length == 3) {
            str = (String) params[0];
            key = (String) params[1];
            index = (Integer) params[2];
        }
        return JsonPathUtil.getJsonValue(str, key, index);
    }

}
