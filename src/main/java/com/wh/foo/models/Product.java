package com.wh.foo.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Description:
 * @Auther: WangHong
 * @Date: 2020/4/16 09:11
 */
@Entity
@Table(name = "foo_product")
public class Product extends BaseEntity{

    /** 删除（0-否， 1-是） */
    private int state = 0;
    /** 名称 */
    private String title;
    /** 商品类型 */
    private ProductType productType;

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


    @ManyToOne
    @JoinColumn(name = "product_type_id")
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
