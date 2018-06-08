package com.mace.ftp.util;

import com.mace.common.Constant;
import com.mace.ftp.config.FTPProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * description: FTP工具类
 * <br />
 * Created by mace on 20:39 2018/5/27.
 */
@Slf4j
public class FTPUtil {

    private FTPClient ftpClient;

    private static String host;
    private static String username;
    private static String password;
    private static String remotePath;
    private static int ip;
    private static int bufferSize;

    static {
        host = FTPProperties.host;
        ip = FTPProperties.ip;
        username = FTPProperties.username;
        password = FTPProperties.password;
        remotePath = FTPProperties.remotePath;
        bufferSize = FTPProperties.bufferSize;
        log.info("host:{}, ip:{}, username:{}, password:{}, remotePath:{}, buffer-szie:{}",
                host,ip,username,"<>",remotePath,bufferSize);
    }


    /**
     * description: 文件上传 支持单文件和多文件
     * <br /><br />
     * create by mace on 2018/5/28 11:17.
     * @param dest     上传远程目录
     * @param fileMap  Map<String, MultipartFile> key：文件名 value：文件
     * @return: boolean
     */
    public static boolean uploadFile(String dest, Map<String, MultipartFile> fileMap) throws IOException {

        return new FTPUtil().upload(remotePath+dest, fileMap);
    }

    /**
     * description:
     * <br /><br />
     * create by mace on 2018/6/7 14:58.
     * @param url           ftp服务器文件路径不包含前缀(ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg)
     * @param savePath      本地保存路径
     * @return: boolean
     */
    public static boolean downloadFile(String url, String savePath) throws IOException {

        return new FTPUtil().download(url, savePath);
    }

    /**
     * description: 返回流,供浏览器 response 下载
     * <br /><br />
     * create by mace on 2018/6/7 15:50.
     * @param url    ftp服务器文件路径不包含前缀(ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg)
     * @return: java.io.InputStream
     */
    public static InputStream downloadFile(String url) throws IOException {

        return new FTPUtil().download(url);
    }



    private boolean upload(String remotePath, Map<String, MultipartFile> fileMap) throws IOException {

        boolean uploaded = true;
        InputStream ins = null;

        if(connectServer(host, ip, username, password)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(bufferSize);
                ftpClient.setControlEncoding(Constant.DEFAULT_ENCODING_CHARTSET);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                Set<Map.Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
                for(Map.Entry<String, MultipartFile> entry : entrySet){
                    String uploadFileName = entry.getKey();
                    ins = entry.getValue().getInputStream();
                    ftpClient.storeFile(uploadFileName, ins);
                }
                log.info("文件上传FTP服务器成功");
            } catch (IOException e) {
                log.error("上传文件异常, {}",e.getMessage());
                uploaded = false;
            } finally {
                ins.close();
                ftpClient.disconnect();
            }
        }else{
            uploaded = false;
            log.error("文件上传FTP服务器失败");
        }
        return uploaded;
    }

    private boolean download(String url, String savePath) throws IOException {

        boolean downloaded = true;

        if(connectServer(host, ip, username, password)){
            try {
                ftpClient.changeWorkingDirectory(getWorkingDirectory(url));
                ftpClient.setBufferSize(bufferSize);
                ftpClient.setControlEncoding(Constant.DEFAULT_ENCODING_CHARTSET);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                String fileName = getFileName(url);
                FTPFile[] files = ftpClient.listFiles();

                for (FTPFile file : files) {
                    if(file.getName().equals(fileName)){
                        File localFile = new File(savePath+fileName);
                        OutputStream os = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(fileName, os);
                        os.close();
                        break;
                    }
                }

                log.info("从FTP服务器下载文件成功");

            } catch (IOException e) {
                log.error("下载文件异常,FTP服务器IO异常, {}",e.getMessage());
                downloaded = false;
            } finally {
                ftpClient.disconnect();
            }
        }else{
            downloaded = false;
            log.error("文件下载失败,FTP服务器登录异常");
        }
        return downloaded;
    }

    private InputStream download(String url) throws IOException {

        if(connectServer(host, ip, username, password)){
            try {
                ftpClient.changeWorkingDirectory(getWorkingDirectory(url));
                ftpClient.setBufferSize(bufferSize);
                ftpClient.setControlEncoding(Constant.DEFAULT_ENCODING_CHARTSET);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();

                String fileName = getFileName(url);
                FTPFile[] files = ftpClient.listFiles();

                for (FTPFile file : files) {
                    if(file.getName().equals(fileName)){
                        return ftpClient.retrieveFileStream(fileName);
                    }
                }

            } catch (IOException e) {
                log.error("下载文件异常,FTP服务器IO异常, {}",e.getMessage());
            } finally {
                ftpClient.disconnect();
            }
        }else{
            log.error("文件下载失败,FTP服务器登录异常");
        }
        return null;
    }

    private boolean connectServer(String ip,int port,String user,String pwd){

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip,port);
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            log.error("连接FTP服务器异常, {}",e.getMessage());
            return isSuccess;
        }
        return isSuccess;
    }

    /**
     * description: 截取文件保存路径 images/other/
     * <br /><br />
     * create by mace on 2018/6/7 15:00.
     * @param url   ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg
     * @return: java.lang.String   images/other/
     */
    private String getWorkingDirectory(String url){

        // 192.168.88.132/images/other/
        String tempPath = url.substring(url.indexOf("/")+2, url.lastIndexOf("/")+1);
        // images/other/
        String saveFileDirectory = tempPath.substring(tempPath.indexOf("/") + 1);
        // /data/ftpfile/images/other/
        return remotePath + saveFileDirectory;
    }

    /**
     * description: 截取ftp服务器上的文件名
     * <br /><br />
     * create by mace on 2018/6/7 15:18.
     * @param url   ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg
     * @return: java.lang.String    5b18c851a623e42c90eb1cbc.jpg
     */
    private String getFileName(String url){

        return url.substring(url.lastIndexOf("/")+1);
    }

}
