package com.wh.foo.controllers;

import com.wh.foo.models.Role;
import com.wh.foo.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description: 角色Controller
 * @Auther: WangHong
 * @Date: 2020/4/8 16:11
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService service;

    /**
     * 角色列表
     *
     * @Param [model]
     * @Author WangHong
     * @Date 16:14 2020/4/8
     * @return java.lang.String
     **/
    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("list", service.findAll());
        return "sys/role/list";
    }

    /**
     * 新增/编辑 角色
     *
     * @Param [id, model]
     * @Author WangHong
     * @Date 16:46 2020/4/8
     * @return java.lang.String
     **/
    @GetMapping({"add", "edit/{id}"})
    public String edit(@PathVariable(value = "id", required = false) Long id, Model model){
        Role role = new Role();
        if(null != id){
            role = service.findOne(id);
        }
        model.addAttribute("entity", role);
        model.addAttribute("permissions", service.findPermissionAll());
        return "sys/role/form";
    }
}
