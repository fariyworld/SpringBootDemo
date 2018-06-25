package com.mace.controller.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 16:24 2018/6/24.
 */
@RestController
@RequestMapping("/swagger")
@Api(tags={"swagger"})
public class TestSwaggerController {

    @ApiOperation(value = "测试 Swagger2 ")
    @ApiImplicitParam(name = "name", value = "say hello 姓名", required = true,
                      dataType = "String", paramType = "path")
    @RequestMapping(value = "test.do/{name}", method = RequestMethod.GET)
    public String test(@PathVariable String name){

        return "Hello " + name;
    }
}
