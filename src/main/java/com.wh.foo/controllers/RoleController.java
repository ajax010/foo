package com.wh.foo.controllers;

import com.wh.foo.core.Servlets;
import com.wh.foo.models.Role;
import com.wh.foo.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

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
     * 分页查询角色信息
     *
     * @Param [pageNumber, pageSize, request, model]
     * @Author WangHong
     * @Date 10:54 2020/4/10
     * @return java.lang.String
     **/
    @GetMapping("list")
    public String list(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "size", defaultValue = "1") int pageSize, ServletRequest request,
                       Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<Role> page = service.findPage(searchParams, pageNumber, pageSize);
        model.addAttribute("page", page);
        model.addAttribute("searchParams", searchParams);
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
