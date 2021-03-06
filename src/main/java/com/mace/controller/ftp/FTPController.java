package com.mace.controller.ftp;

import com.mace.common.Constant;
import com.mace.common.ResponseMessage;
import com.mace.ftp.service.IFTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 20:37 2018/5/27.
 */
@RestController
@RequestMapping("/ftp")
@Slf4j
public class FTPController {

    @Autowired
    private IFTPService iftpService;


    /**
     * description: 单文件上传  上传文件至 images/other/
     * <br /><br />
     * create by mace on 2018/5/27 21:24.
     * @param file
     * @return: com.mace.common.ResponseMessage<java.util.Map<java.lang.String,java.lang.String>>
     */
    @RequestMapping(value = "uploadFile.do")
    public ResponseMessage<Map<String,String>> uploadFile(
            @RequestParam(value = "photo", required = false) MultipartFile file){

        return iftpService.uploadFile(Constant.FTP_OTHER_IMAGES_PATH, file);
    }


    /**
     * description:     多文件上传   上传文件至 images/other/
     * <br /><br />
     * create by mace on 2018/5/28 16:02.
     * @param name      input type="file" 的 name属性
     * @param request
     * @return: com.mace.common.ResponseMessage<java.util.Map<java.lang.String,java.lang.String>>
     */
    @RequestMapping(value = "uploadMultiFile.do")
    public ResponseMessage<Map<String,String>> uploadMultiFile(
            @RequestParam(value = "name", required = false, defaultValue = Constant.DEFAULT_MULTI_FILE_NAME) String name,
            HttpServletRequest request){

        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles(name);

        //文件上传个数限制
        if(fileList.size() > Constant.DEFAULT_LIMIT_FILE_SIZE)
            return ResponseMessage.createByErrorMessage("上传文件个数超出限制... 最多可上传"+Constant.DEFAULT_LIMIT_FILE_SIZE+"个文件");
        else
            return iftpService.uploadFile(Constant.FTP_OTHER_IMAGES_PATH, fileList);
    }


//    ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg


//    @RequestMapping(value = "downloadFile.do")
//    public ResponseMessage<String> downloadFile(){
//
//        String savePath = "D:\\WebLogs\\SpringBootDemo\\download\\images\\other\\";
//        String url = "ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg";
//
//        return iftpService.downloadFile(url, savePath);
//    }


    @RequestMapping(value = "downloadFile.do")
    public void downloadFile(String url, HttpServletResponse response){

        iftpService.downloadFile(url, response);
    }
}
