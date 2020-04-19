package com.wh.foo.repository;

import com.wh.foo.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 商品类型Dao接口
 * @Auther: WangHong
 * @Date: 2020/4/16 09:17
 */
public interface ProductTypeDao extends JpaRepository<ProductType, Long> {
}
