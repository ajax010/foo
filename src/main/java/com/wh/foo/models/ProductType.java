package com.wh.foo.models;

import com.google.common.collect.Lists;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @Description: 商品类型
 * @Auther: WangHong
 * @Date: 2020/4/16 09:10
 */
@Entity
@Table(name = "foo_product_type")
public class ProductType extends BaseEntity{

    /** 删除（0-否， 1-是） */
    private int state = 0;
    /** 名称 */
    private String title;
    /** 关联商品 */
    private List<Product> productList = Lists.newArrayList();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
