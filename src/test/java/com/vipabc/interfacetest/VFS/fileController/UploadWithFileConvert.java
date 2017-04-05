package com.vipabc.interfacetest.VFS.fileController;

import com.vipabc.interfacetest.utils.Env;
import com.vipabc.interfacetest.utils.JsonPathUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shelper.datadrive.ExcelProvider;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by oliverluan on 01/04/2017.
 */
public class UploadWithFileConvert extends Env{
    @Test(priority = 1, dataProvider = "data", description = "单个上传文件同时格式转换（PPT/PPTX 转PNG）", groups = {"functiontest"})
    public void uploadWithFileConvert(Map<String, String> dataDriven) throws IOException, SQLException {

        String callback = "172.16.233.82:8098/v1/test/post";
        String extension = dataDriven.get("extension");
        String convertTo = dataDriven.get("convertTo");
        String uploader = dataDriven.get("uploader");
        String location = dataDriven.get("location");
        String scope = dataDriven.get("scope");
        String filePath = System.getProperty("user.dir") + File.separator + "SIP.ppt";
        String response = UploadWithFileConvertMsg.uploadWithFileConvert(callback, extension, convertTo, uploader, location, scope, filePath);
        Assert.assertTrue(response.contains("\"success\":true"));
        Assert.assertTrue(response.contains("\"result\":{\"fileId\":\""));

        String fileId = JsonPathUtil.parseJsonPath(response, "$..fileId", 1);
        int rtn = ctd.query("SELECT * FROM t_file_convert_from WHERE id = '" + fileId + "' AND status = '0' ORDER BY created_at DESC").size();
        Assert.assertEquals(rtn, 1);
    }

    @DataProvider
    public Iterator<Object[]> data() {
        System.out.println(this.getClass().toString());
        return new ExcelProvider(this, "uploadWithFileConvert");
    }

    @AfterMethod
    public void afterTest() {

    }


}
