package com.vipabc.interfacetest.backend.homeworkController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 22/03/2017.
 */
public class GetHomeworkMsg {
    public static void getHomework(HttpFixture hf, String d)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server.vipabc","url")+"/HomeworkIMO/homework/GetHomework?d=" + d;

        hf.setHeaderValue("Content-Type", "text/html; charset=utf-8");

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();

    }
}
