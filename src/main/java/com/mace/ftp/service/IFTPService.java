package com.mace.ftp.service;

import com.mace.common.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 20:38 2018/5/27.
 */
public interface IFTPService {

    /**
     * description: 单文件上传
     * <br /><br />
     * create by mace on 2018/5/28 11:13.
     * @param dest 上传远程目录(示例：images/other/) 注意 路径最后的斜杠
     * @param file 上传文件
     * @return: com.mace.common.ResponseMessage<Map<String,String>> key: uri vlaue:url
     */
    ResponseMessage<Map<String,String>> uploadFile(String dest,  MultipartFile file);

    /**
     * description: 多文件上传
     * <br /><br />
     * create by mace on 2018/5/28 11:46.
     * @param dest     上传远程目录(示例：images/other/) 注意 路径最后的斜杠
     * @param fileList 上传文件集合
     * @return: com.mace.common.ResponseMessage<Map<String,String>> key: uri vlaue:url
     */
    ResponseMessage<Map<String,String>> uploadFile(String dest, List<MultipartFile> fileList);

    /**
     * description: 单文件下载
     * <br /><br />
     * create by mace on 2018/6/7 12:11.
     * @param url       业务需要下载的 url
     * @param savePath  本地保存路径
     * @return: com.mace.common.ResponseMessage<java.lang.String>
     */
    ResponseMessage<String> downloadFile(String url, String savePath);


    /**
     * description: 浏览器下载ftp服务器上的文件
     * <br /><br />
     * create by mace on 2018/6/7 15:52.
     * @param url   业务需要下载的 url
     * @param response
     * @return: com.mace.common.ResponseMessage<java.lang.String>
     */
    ResponseMessage<String> downloadFile(String url, HttpServletResponse response);

}
