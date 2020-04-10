package com.wh.foo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Auther: WangHong
 * @Date: 2020/3/27 14:24
 * @Description: 首页
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 首页
     *
     * @Param [model]
     * @Author WangHong
     * @Date 18:16 2020/3/28
     * @return java.lang.String
     **/
    @GetMapping(value = "/index")
    public String index(){
        return "home/index";
    }
}
