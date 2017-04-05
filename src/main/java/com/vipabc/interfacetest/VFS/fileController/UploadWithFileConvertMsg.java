package com.vipabc.interfacetest.VFS.fileController;

import com.vipabc.interfacetest.backend.BaseMsg;
import com.vipabc.interfacetest.utils.MultipartUtility;
import com.vipabc.interfacetest.utils.Pro;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by oliverluan on 01/04/2017.
 */
public class UploadWithFileConvertMsg extends BaseMsg{
    public static String uploadWithFileConvert(String callback, String extension, String convertTo, String uploader, String location, String scope, String filePath) {
        Pro pro = new Pro();
        String request = pro.getEnvPropties("vfs.server", "url") + "/v1/file/uploadWithFileConvert";
        System.out.println("request: " + request);

        String charset = "UTF-8";
        File uploadFile = new File(filePath);
        List<String> response = null;

        try {
            MultipartUtility multipart = new MultipartUtility(request, charset);

//            multipart.addHeaderField("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryt95PQ5gOt0PPlQkv");

            multipart.addFormField("callback", callback);
            multipart.addFormField("extension", extension);
            multipart.addFormField("convertTo", convertTo);
            multipart.addFormField("uploader", uploader);
            multipart.addFormField("location", location);
            multipart.addFormField("scope", scope);
            multipart.addFilePart("file", uploadFile);

            response = multipart.finish();

            System.out.println("SERVER REPLIED:");

            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return response.toString();

    }

}
