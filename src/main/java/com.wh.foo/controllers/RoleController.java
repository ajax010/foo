package com.wh.foo.controllers;

import com.wh.foo.core.Message;
import com.wh.foo.core.Servlets;
import com.wh.foo.models.Role;
import com.wh.foo.services.RoleService;
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
        model.addAttribute("permissionGroup", service.getPermissionAllGroup());
        return "sys/role/form";
    }

    /**
     * 保存角色信息
     *
     * @Param [entity, permissionIds, redirectAttributes]
     * @Author WangHong
     * @Date 17:18 2020/4/11
     * @return java.lang.String
     **/
    @PostMapping("save")
    public String save(@Valid @ModelAttribute("entity") Role entity, String permissionIds, RedirectAttributes redirectAttributes){
        try {
            service.save(entity, permissionIds);
            redirectAttributes.addFlashAttribute(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute(Message.ERROR);
        }
        return "redirect:/role/list";
    }

    /**
     * 请求该Controller里的任何方法时会先调用本方法,当更新页面只更新部分字段时,需要添加该方法.
     */
    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("entity", service.findOne(id));
        }
    }
}
