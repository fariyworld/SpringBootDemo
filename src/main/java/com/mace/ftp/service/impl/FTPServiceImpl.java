package com.mace.ftp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mace.common.ResponseCode;
import com.mace.common.ResponseMessage;
import com.mace.ftp.config.FTPProperties;
import com.mace.ftp.service.IFTPService;
import com.mace.ftp.util.FTPUtil;
import com.mace.mongodb.util.MongoOpsUtil;
import com.mace.util.CheckParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * <br />
 * Created by mace on 20:38 2018/5/27.
 */
@Service("iftpService")
@Slf4j
public class FTPServiceImpl implements IFTPService {

    @Override
    public ResponseMessage<Map<String,String>> uploadFile(String dest, MultipartFile file) {

        return uploadFile(dest, Lists.newArrayList(file));
    }

    @Override
    public ResponseMessage<Map<String,String>> uploadFile(String dest, List<MultipartFile> fileList){

        Map<String, MultipartFile> fileMap =  Maps.newHashMap();

        for(MultipartFile file: fileList){

            String fileName = file.getOriginalFilename();
            String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
            String uploadFileName = MongoOpsUtil.ObjectId2Str() + fileExtensionName;
            fileMap.put(uploadFileName, file);

            log.info("开始上传文件,上传文件的文件名:{},新文件名:{}",fileName,uploadFileName);
        }

        try {

            boolean flag = FTPUtil.uploadFile(dest, fileMap);

            if(flag){
                //上传成功
                Map<String,String> res = Maps.newHashMap();
                Set<String> keySet = fileMap.keySet();
                for(String uploadFileName: keySet){

                    String url = FTPProperties.prefix + dest + uploadFileName;
                    res.put(uploadFileName, url);
                }
                return ResponseMessage.createBySuccess("文件上传FTP服务器成功",res);
            }

        } catch (IOException e) {
            log.error("文件上传FTP服务器失败");
            return ResponseMessage.createByErrorMessage("文件上传失败");
        }
        return ResponseMessage.createByErrorMessage("文件上传FTP服务器失败");
    }


    @Override
    public ResponseMessage<String> downloadFile(String url, String savePath) {

        // 校验文件名是否有效
        if(CheckParamUtil.isEffective(url)){

            // 设置保存文件路径
            File saveFileisDirectory = new File(savePath);
            if(!saveFileisDirectory.exists() && !saveFileisDirectory.isDirectory()){
                saveFileisDirectory.mkdirs();
                log.info("创建保存文件的根目录成功, {}",savePath);
            }

            try {
                boolean downloaded = FTPUtil.downloadFile(url, savePath);

                if(downloaded)
                    return ResponseMessage.createBySuccessMessage("文件下载成功："+url);
                else
                    return ResponseMessage.createByErrorMessage("文件下载失败："+url);

            } catch (IOException e) {
                log.error("从FTP服务器下载文件失败, {}",e.getMessage());
                return ResponseMessage.createByErrorMessage("文件下载失败："+url);
            }
        }
        return ResponseMessage.createByErrorResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
    }

    @Override
    public ResponseMessage<String> downloadFile(String url, HttpServletResponse response) {
        // 校验文件名是否有效
        if(CheckParamUtil.isEffective(url)){

            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + getFileName(url));
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            InputStream ins = null;
            OutputStream os = null;
            try {

                ins = FTPUtil.downloadFile(url);
                bis = new BufferedInputStream(ins);
                os = response.getOutputStream();

                int i = -1;

                while ((i = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, i);
                }
                log.info("下载成功：{}",url);
                return ResponseMessage.createBySuccessMessage("下载成功："+ url);

            } catch (IOException e) {
                log.error("IO异常, {}",e.getMessage());
            }finally {
                if(os != null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        log.error("IO异常, {}",e.getMessage());
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        log.error("IO异常, {}",e.getMessage());
                    }
                }
                if (ins != null) {
                    try {
                        ins.close();
                    } catch (IOException e) {
                        log.error("IO异常, {}",e.getMessage());
                    }
                }
            }
        }
        return ResponseMessage.createByErrorResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
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
