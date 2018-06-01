package com.mace.ftp.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mace.common.ResponseMessage;
import com.mace.ftp.config.FTPProperties;
import com.mace.ftp.service.IFTPService;
import com.mace.ftp.util.FTPUtil;
import com.mace.mongodb.util.MongoOpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

}
