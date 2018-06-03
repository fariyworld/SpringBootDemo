package com.mace.controller.redis;

import com.mace.common.ResponseMessage;
import com.mace.redis.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IRedisService iRedisService;

    @RequestMapping(value = "testRedisSpringSession.do")
    public ResponseMessage<String> testRedisSpringSession(HttpSession session){

        session.setAttribute("spring", "testRedisSpringSession,你好");

        return ResponseMessage.createBySuccessMessage("测试成功");
    }

    @RequestMapping(value = "set.do")
    public ResponseMessage<String> set(String key, String vlaue, long time){

        iRedisService.setForTimeSeconds(key, vlaue, time);

        return ResponseMessage.createBySuccessMessage("redis设值成功");
    }

}
