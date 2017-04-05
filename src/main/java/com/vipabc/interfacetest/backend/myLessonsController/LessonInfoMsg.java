package com.vipabc.interfacetest.backend.myLessonsController;

import com.vipabc.interfacetest.utils.EncodingUtil;
import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 17/03/2017.
 */
public class LessonInfoMsg {
    public static void lessonInfo(HttpFixture hf, String sessionTime, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/MyLessons/LessonInfo";

        hf.setHeaderValue("Content-Type", "text/html; charset=utf-8");
        hf.addParamValue("sessiontime", EncodingUtil.encodeURIComponent(sessionTime));
        hf.addCookieValue("Cookie", cookies);

        hf.setUrl(request);
        System.out.println(request);
        hf.Get();

    }
}
