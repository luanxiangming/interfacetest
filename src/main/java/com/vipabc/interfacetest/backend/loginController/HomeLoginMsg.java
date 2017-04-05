package com.vipabc.interfacetest.backend.loginController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

public class HomeLoginMsg {
	public static String login(HttpFixture hf)
	{
		Pro pro=new Pro();
		String request=pro.getEnvPropties("url.server","url")+"/Home/Login";
		hf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.setUrl(request);
		System.out.println(request);
		hf.setRequestBody("txt_login_account=QASusanTest2016120902%40test.com&pwd_login_password=123456");
		hf.Post();
		return hf.saveParamLeftstrRightstr("PlainResult\":\"", "\"}");
	}

}
