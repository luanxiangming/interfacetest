package com.vipabc.interfacetest.backend.loginController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 07/03/2017.
 */
public class NewLoginMsg extends BaseMsg {
    public static String newLogin(HttpFixture hf, String account, String password, boolean rememberMe, boolean fromIms) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("url.server", "url") + "/Login/NewLogin";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody = "{\"Account\":\"" + account + "\", \"Password\":\"" + password + "\", \"RememberMe\":\"" + rememberMe + "\", \"FromIms\":\"" + fromIms + "\"}";
        hf.setRequestBody(reqBody);
        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
        return hf.getResponseheaders("Set-Cookie").replaceAll("Set-Cookie=", "");
    }

    /**
     * V大人登录，需要调用两个请求
     *
     * @param account
     * @param password
     */
    public static void newLoginV(String account, String password) {
        String request = prop.getEnvPropties("url.server.v", "url") + "/Home/Login";
        String reqBody = "txt_login_account=" + account + "&pwd_login_password=" + password;
        htf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        htf.setRequestBody(reqBody);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
        request = prop.getEnvPropties("url.server.v", "url") + "/Center/Login/LoginTransForm?chk_remember_account=N&returnurl=null";
        reqBody = "txt_login_account=" + account + "&pwd_login_password=" + password;
        htf.setRequestBody(reqBody);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void newLoginVjr(String account, String password, boolean rememberMe, boolean fromIms) {
        String request = prop.getEnvPropties("url.server", "url") + "/Login/NewLogin";
        htf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody = "{\"Account\":\"" + account + "\", \"Password\":\"" + password + "\", \"RememberMe\":\"" + rememberMe + "\", \"FromIms\":\"" + fromIms + "\"}";
        htf.setRequestBody(reqBody);
        htf.setUrl(request);
        System.out.println(request);
        htf.Post();
    }

    public static void newLoginVjr(String account, String password) {
        newLoginVjr(account, password, false, false);
    }

    public static void login(String account, String password) {
        if (brandId == 4) {
            newLoginVjr(account, password);
        } else {
            newLoginV(account, password);
        }
    }
}
