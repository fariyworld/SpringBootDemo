package com.mace.controller.test;

import com.mace.common.ResponseMessage;
import com.mace.common.RestPackResponse;
import com.mace.common.RestPackResponseCode;
import com.mace.domain.User;
import com.mace.service.mysql.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * description:
 * <br />
 * Created by mace on 16:24 2018/6/24.
 */
@RestController
//@RestPackController
@RequestMapping("/swagger")
@Api(tags={"swagger"})
public class TestSwaggerController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "测试 Swagger2 ")
    @ApiImplicitParam(name = "name", value = "say hello 姓名", required = true,
                      dataType = "String", paramType = "path")
    @RequestMapping(value = "test.do/{name}", method = RequestMethod.GET)
    public RestPackResponse<String> test(@PathVariable String name) {

        String result = "Hello " + name;

        return RestPackResponse.createBySuccess("测试成功", result);
    }

    @ApiOperation(value = "测试 aop 封装 restpack exception")
    @ApiImplicitParam(name = "name", value = "用户名", required = true,
            dataType = "String", paramType = "path")
    @RequestMapping(name = "测试 aop 封装 restpack exception", value = "register.do/{name}", method = RequestMethod.POST)
    public RestPackResponse<String> register(@PathVariable String name){

        User user = new User();
        user.setUsername(name);

        ResponseMessage<String> register = iUserService.register(user);

        if(!register.isSuccess()){
            HashMap<String, String> props = new HashMap<>();

            props.put("username", user.getUsername());

            return RestPackResponse.createByError(
                    RestPackResponseCode.User.NAME_DUPLICATE.getResultCode(),
                    RestPackResponseCode.User.NAME_DUPLICATE.getMessage().replace("{1}", user.getUsername()),
                    props
            );
        }
        return null;
    }

    @RequestMapping("testEx.do")
    @ResponseBody
    public String testEx() throws Exception{

        int i = 1/0;

        return "success";
    }
}
