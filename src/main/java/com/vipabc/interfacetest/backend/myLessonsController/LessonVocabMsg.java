package com.vipabc.interfacetest.backend.myLessonsController;

import com.vipabc.interfacetest.utils.Pro;
import shelper.iffixture.HttpFixture;

/**
 * Created by oliverluan on 20/03/2017.
 */
public class LessonVocabMsg {
    public static void lessonVocab(HttpFixture hf, String materialSn, String period, String materialBrand, String cookies)
    {
        Pro pro=new Pro();
        String request=pro.getEnvPropties("member.server","url")+"/aspx/IMO/MyLessons/LessonVocab";

        hf.setHeaderValue("Content-Type", "application/x-www-form-urlencoded");
        hf.addCookieValue("Cookie", cookies);
        hf.addParamValue("materialSn", materialSn);
        hf.addParamValue("period", period);
        hf.addParamValue("materialBrand", materialBrand);

        hf.setUrl(request);
        System.out.println(request);
        hf.Post();

    }
}
