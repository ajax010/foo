package com.wh.foo.controllers;

import com.wh.foo.core.Message;
import com.wh.foo.core.Servlets;
import com.wh.foo.models.User;
import com.wh.foo.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Description: 用户管理Controller
 * @Auther: WangHong
 * @Date: 2020/3/28 18:29
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService service;

    /**
     * 用户列表
     *
     * @Param [pageNumber, pageSize, model, request]
     * @Author WangHong
     * @Date 18:52 2020/3/28
     * @return java.lang.String
     **/
    @GetMapping("list")
    public String list(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "size", defaultValue = "1") int pageSize, ServletRequest request,
                       Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<Map<String, Object>> page = service.getPage(searchParams, pageNumber, pageSize);
        model.addAttribute("page", page);
        model.addAttribute("searchParams", searchParams);
        return "sys/user/list";
    }

    /**
     * 新增编辑页面
     *
     * @Param [id, model]
     * @Author WangHong
     * @Date 15:55 2020/4/7
     * @return java.lang.String
     **/
    @GetMapping({"add", "edit/{id}"})
    public String add(@PathVariable(value = "id", required = false) Long id, Model model){
        User user = new User();
        if(null != id){
            user = service.findByStateAndId(0, id);
        }
        model.addAttribute("entity", user);
        return "sys/user/form";
    }

    /**
     * 保存用户信息
     *
     * @Param [entity, redirectAttributes]
     * @Author WangHong
     * @Date 16:07 2020/4/7
     * @return java.lang.String
     **/
    @PostMapping("save")
    public String save(@Valid @ModelAttribute("entity") User entity, RedirectAttributes redirectAttributes){
        try {
           service.save(entity);
            redirectAttributes.addFlashAttribute(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(Message.ERROR);
        }
        return "redirect:/user/list";
    }

    /**
     * 校验账号是否重复
     *
     * @Param [id, username]
     * @Author WangHong
     * @Date 9:33 2020/4/8
     * @return java.lang.String
     **/
    @PostMapping("username_is_exists")
    public @ResponseBody String usernameIsExists(String id, String username){
        if(StringUtils.isBlank(username)){
            return "false";
        }

        User user = service.findByStateAndUsername(0, username);
        if(null == user){
            return "true";
        }

        if(StringUtils.isNotBlank(id) && StringUtils.equals(user.getId().toString(), id)){
            return "true";
        }

        return "false";
    }

    /**
     * 删除用户
     *
     * @Param [id, redirectAttributes]
     * @Author WangHong
     * @Date 14:05 2020/4/8
     * @return java.lang.String
     **/
    @PostMapping("del")
    public String del(Long id, RedirectAttributes redirectAttributes){
        if(null == id){
            redirectAttributes.addFlashAttribute(Message.ERROR);
            return "redirect:/user/list";
        }

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(Message.ERROR);
        }
        return "redirect:/user/list";
    }


    /**
     * 重置用户密码
     *
     * @Param [id]
     * @Author WangHong
     * @Date 14:00 2020/4/8
     * @return java.lang.String
     **/
    @PostMapping("reset_password")
    public @ResponseBody String resetPassword(String id){
        if(StringUtils.isBlank(id)){
            return "false";
        }

        User user = service.findByStateAndId(0, Long.parseLong(id));
        if(null == user){
            return "false";
        }

        try {
            service.resetPassword(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "false";
        }
        return "true";
    }

    /**
     * 请求该Conntroller里的任何方法时会先调用本方法,当更新页面只更新部分字段时,需要添加该方法.
     */
    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("entity", service.findByStateAndId(0, id));
        }
    }
}
