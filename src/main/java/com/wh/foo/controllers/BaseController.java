package com.wh.foo.controllers;

import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;

import java.util.Map;

/**
 *
 * @Description: Controller 基类
 * @Auther: WangHong
 * @Date: 2020/3/28 18:07
 */
public class BaseController {

    private static final Gson gson = new Gson();

    /**
     * 获取当前登录用户信息
     *
     * @Param []
     * @Author WangHong
     * @Date 18:15 2020/3/28
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String, Object> getSubjectPrincipal(){
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if(null == obj){
            throw new RuntimeException("登录账户异常！");
        }

        Map<String, Object> map = gson.fromJson(gson.toJson(obj), Map.class);
        return map;
    }
}
