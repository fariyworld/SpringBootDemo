package com.mace.controller.ajax;

import com.mace.common.ResponseCode;
import com.mace.common.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 21:48 2018/5/27.
 */
@RestController
public class AjaxController {

    @RequestMapping(value = "/testAjax.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage testAjax(String username, String password){

        if(StringUtils.isBlank(password) || StringUtils.isBlank(password)){
            return ResponseMessage.createByErrorResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
        }

        if("admin".equals(username) && "admin".equals(password)){
            return ResponseMessage.createBySuccessMessage("登录成功");
        }

        return ResponseMessage.createByErrorMessage("登录失败");
    }
}
