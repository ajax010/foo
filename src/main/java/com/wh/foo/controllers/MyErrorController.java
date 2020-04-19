package com.wh.foo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 4401 404 500 错误拦截
 * @Auther: WangHong
 * @Date: 2020/4/7 09:23
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 500){
            return "/error/500";
        }else if(statusCode == 404){
            //对应的是/error/404.html、/error/404.jsp等，文件位于/templates下面
            return "/error/404";
        }else if(statusCode == 403){
            return "/403";
        }else{
            return "/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
