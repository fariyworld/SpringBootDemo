package com.mace.ftp.util;

import com.mace.common.Constant;
import com.mace.ftp.config.FTPProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
     * description: 多文件上传
     * <br /><br />
     * create by mace on 2018/5/28 11:17.
     * @param dest     上传远程目录
     * @param fileMap  Map<String, MultipartFile> key：文件名 value：文件
     * @return: boolean
     */
    public static boolean uploadFile(String dest, Map<String, MultipartFile> fileMap) throws IOException {

        return new FTPUtil().upload(remotePath+dest, fileMap);
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
}
