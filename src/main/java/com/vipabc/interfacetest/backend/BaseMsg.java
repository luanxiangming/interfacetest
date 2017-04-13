package com.vipabc.interfacetest.backend;

import com.vipabc.interfacetest.utils.ConnectToDB;
import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;


/**
 * Created by tao_twang on 2017/3/23.
 */
public class BaseMsg {

    public static HttpFixture htf = new HttpFixture();
    public static ConnectToDB ctd = new ConnectToDB();

    public static Pro prop = new Pro();
    public static int brandId = Integer.valueOf(prop.getEnvPropties("brandId", "env"));

    public BaseMsg() {
    }

    public static void setHttpFixture(HttpFixture httpFixture) {
        BaseMsg.htf = httpFixture;
    }

    public static void setConnectToDB(ConnectToDB connectToDB) {
        BaseMsg.ctd = connectToDB;
    }
}
