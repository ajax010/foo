package com.wh.foo.controllers;

import com.google.gson.Gson;
import com.wh.foo.models.User;
import com.wh.foo.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 15:11
 * @Description:
 */
@Controller
public class LoginController {

    private static final Gson gson = new Gson();

    private static final String HOME_PATH = "/";

    @Resource
    private UserService userService;

    @GetMapping({"/", "/login"})
    public String login(HttpServletRequest request){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if(StringUtils.equals(savedRequest.getRequestUrl(), "/")){
                return "redirect:/home/index";
            }

            String serverName = request.getServerName();
            int serverPort = request.getServerPort();
            StringBuffer url = new StringBuffer();
            url.append(serverName + ":");
            url.append(serverPort + "/");
            url.append(savedRequest.getRequestUrl());
            return "redirect://" + url.toString();
        } else {
            return "login";
        }
    }

    /**
     * 登录验证
     *
     * @Param [username, password]
     * @Author WangHong
     * @Date 15:17 2020/3/27
     * @return java.lang.String
     **/
    @GetMapping("doLogin")
    public @ResponseBody
    String doLogin(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", "000");
        map.put("msg", "success");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            map.put("code", "001");
            map.put("msg", "用户名不存在或密码不匹配!");
            return gson.toJson(map);
        } catch (UnknownAccountException uae) {
            map.put("code", "001");
            map.put("msg", "用户名不存在或密码不匹配!");
            return gson.toJson(map);
        }

        User user = userService.findUserByName(username);
        subject.getSession().setAttribute("user", user);
        map.put("homePath", HOME_PATH);
        return gson.toJson(map);
    }
}
