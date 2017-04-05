package shelper.utils;/*package shelper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.testng.Reporter;

public class MySftp {



    private ChannelSftp sftp;
    private Session sshSession;
    *//**
     * 连接sftp服务器
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return
     *//*
    public  MySftp(String host, int port, String username,
            String password) {
        ChannelSftp thesftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            Reporter.log("SFTP Session created.", true);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Reporter.log("SFTP Session connected.", true);
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            thesftp = (ChannelSftp) channel;
            Reporter.log("Connected to " + host + ".", true);


            
            
            
        } catch (Exception ex) {
            Reporter.log(ex.getMessage(), true);
        }
        this.sftp=thesftp;
    }

    *//**
     * 上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     *//*
    public void upload(String directory, String uploadFile) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }

    *//**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     *//*
    public void download(String directory, String downloadFile, String saveFile) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }
    *//**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     *//*
    public void delete(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }
    public void disconnect(){
        sshSession.disconnect();
    }
    *//**
     * 列出目录下的文件
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     *//*
    public Vector listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }
     *//**
     * 获得ChannelSftp实例。通过这个实例进行其他的sftp操作
     *//*
     public ChannelSftp getChannelSftp(){
        return this.sftp;
     }
    public static void main(String[] args) {
        String host = "192.168.63.106";
        int port = 22;
        String username = "qa_test";
        String password = "qa_test";
        String directory = "/opt/log/test/";
        String uploadFile = "c:\\test123";
        MySftp sf = new MySftp(host, port, username, password);
        sf.upload(directory, uploadFile);
        sf.disconnect();
    }
}
*/