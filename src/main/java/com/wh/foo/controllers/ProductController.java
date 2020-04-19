package com.wh.foo.controllers;

import com.wh.foo.core.Servlets;
import com.wh.foo.services.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @Description: 商品Controller
 * @Auther: WangHong
 * @Date: 2020/4/16 09:21
 */
@Controller
@RequestMapping("/product/")
public class ProductController extends BaseController{

    @Resource
    private ProductService service;

    /**
     * 商品列表
     *
     * @Param [pageNumber, pageSize, request, model]
     * @Author WangHong
     * @Date 9:22 2020/4/16
     * @return java.lang.String
     **/
    @GetMapping("list")
    @RequiresPermissions(value = {"productType:list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "size", defaultValue = "20") int pageSize, ServletRequest request,
                       Model model){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//        Page<Map<String, Object>> page = service.getPage(searchParams, pageNumber, pageSize);
//        model.addAttribute("page", page);
//        model.addAttribute("searchParams", searchParams);
        return "sys/user/list";
    }
}
