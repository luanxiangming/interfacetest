package com.vipabc.interfacetest.backend.accountController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 24/03/2017.
 */
public class NewClassRecordsMsg {
    public static void newClassRecords(HttpFixture hf, String contractSn, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/Account/NewClassRecords";

        hf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        hf.addCookieValue("Cookie", cookies);
        hf.addParamValue("contractSn", contractSn);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
