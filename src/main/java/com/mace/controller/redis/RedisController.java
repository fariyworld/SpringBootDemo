package com.mace.controller.redis;

import com.mace.common.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description:
 * <br />
 * Created by mace on 20:19 2018/5/29.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @RequestMapping(value = "testRedisSpringSession.do")
    public ResponseMessage<String> testRedisSpringSession(HttpSession session){

        session.setAttribute("spring", "testRedisSpringSession,你好");

        return ResponseMessage.createBySuccessMessage("测试成功");
    }

}
