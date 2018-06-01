package com.mace.controller.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description:
 * <br />
 * Created by mace on 21:46 2018/5/27.
 */
@Controller
public class FreemarkerController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map){

        map.addAttribute("name", "hello freeMarker");

        map.addAttribute("sex", 1);

        return "home";
    }
}
