package com.vipabc.interfacetest.backend.clientApiCaller;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 08/03/2017.
 */
public class RegisterVipjrMsg {
    public static void registerVipjr(HttpFixture hf, int age, int brandId, String cname, String ename, String countryId, String email, int gender, String parentMail, String password, String mobile, String reasonId, String SourceType, String birthday, String phone, int parentFlag)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("login.server","url")+"/loginapi/register";
        hf.setHeaderValue("Content-Type", "application/json; charset=utf-8");
        String reqBody="{\"age\": " + age + ","  +
                        "\"brandId\":\"" + brandId + "\"," +
                        "\"cname\":\"" + cname + "\"," +
                        "\"ename\":\"" + ename + "\"," +
                        "\"countryId\":\"" + countryId + "\"," +
                        "\"email\":\"" + email + "\"," +
                        "\"gender\":\"" + gender + "\"," +
                        "\"parentMail\":\"" + parentMail + "\"," +
                        "\"password\":\"" + password + "\"," +
                        "\"mobile\":\"" + mobile + "\"," +
                        "\"reasonId\":\"" + reasonId + "\"," +
                        "\"SourceType\":\"" + SourceType + "\"," +
                        "\"birthday\":\"" + birthday + "\"," +
                        "\"phone\":\"" + phone + "\"," +
                        "\"parentFlag\":" + parentFlag +
        "}";
        hf.setRequestBody(reqBody);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();
    }
}
