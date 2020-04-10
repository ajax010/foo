/**
 * @(#)LogInterceptor.java
 * Description:
 * Version :	
 * Copyright:	Copyright (c) 诚德软件 版权所有
 */
package com.wh.foo.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 菜单拦截器
 * @Auther: WangHong
 * @Date: 2020/4/07 11:47
 */
public class MenuInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String menuId = request.getParameter("menuId");
        if(StringUtils.isNotBlank(menuId)){
            request.getSession().setAttribute("menuId", menuId);
        }
        return true;
    }
}
