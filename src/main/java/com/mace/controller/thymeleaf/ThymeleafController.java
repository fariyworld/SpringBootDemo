package com.mace.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description:
 * <br />
 * Created by mace on 21:47 2018/5/27.
 */
@Controller
public class ThymeleafController {

    @RequestMapping(value = "/testThymeleaf.do", method = RequestMethod.GET)
    public String testThymeleaf(ModelMap map){

        map.addAttribute("hello", "hello springboot Thymeleaf");

        return "HelloThymeleaf";
    }
}
