package com.wh.foo.controllers;

import com.wh.foo.services.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description: 商品类型Controller
 * @Auther: WangHong
 * @Date: 2020/4/16 09:20
 */
@Controller
@RequestMapping("/product_type/")
public class ProductTypeController extends BaseController{

    @Resource
    private ProductTypeService service;
}
