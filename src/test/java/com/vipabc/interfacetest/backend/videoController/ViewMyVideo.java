package com.vipabc.interfacetest.backend.videoController;

import com.vipabc.interfacetest.backend.loginController.NewLoginMsg;
import com.vipabc.interfacetest.backend.myLessonsController.LessonInfoMsg;
import com.vipabc.interfacetest.utils.Env;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;
import shelper.iffixture.HttpFixture;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 20/03/2017.
 */
public class ViewMyVideo extends Env {
    HttpFixture hf = new HttpFixture();
    @Test(priority = 1, dataProvider = "data", description = "课程录像", groups = {"functiontest"})
    public void viewMyVideo(Map<String, String> dataDriven) throws IOException {
        String cookies = NewLoginMsg.newLogin(hf, dataDriven.get("account"), dataDriven.get("password"), Boolean.valueOf(dataDriven.get("rememberMe")), Boolean.valueOf(dataDriven.get("fromIms")));
        hf.nextRequest();

        //这里通过LessonInfo获取sessionSn
        LessonInfoMsg.lessonInfo(hf, dataDriven.get("sessionTime"), cookies);
        String sessionSn = hf.saveParamLeftstrRightstr("window.LessonInfo.sessionSn = \"", "\"");
        hf.nextRequest();

        ViewMyVideoMsg.viewMyVideo(hf, sessionSn, cookies);
        Assert.assertEquals(hf.getStatus(), 200);

    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "viewMyVideo");
    }
}
