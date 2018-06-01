package com.mace.controller.oracle;

import com.mace.common.ResponseMessage;
import com.mace.entity.Dept;
import com.mace.service_oracle.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * <br />
 * Created by mace on 11:28 2018/5/30.
 */
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    private IDeptService iDeptService;

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ResponseMessage<String> register(Dept dept){

        return iDeptService.register(dept);
    }
}
