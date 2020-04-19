package com.wh.foo.services;

import com.wh.foo.repository.ProductDao;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: 商品类型Service
 * @Auther: WangHong
 * @Date: 2020/4/16 09:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductTypeService {

    @Resource
    private ProductDao productDao;

    @Transactional(readOnly = true)
    public Page<Map<String ,Object>> getPage(final Map<String, Object> searchParams, final int pageNumber, final int pageSize){
        return null;
    }
}
