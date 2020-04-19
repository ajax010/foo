package com.wh.foo.repository;

import com.wh.foo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 商品Dao接口
  @Auther: WangHong
 * @Date: 2020/4/16 09:18
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
