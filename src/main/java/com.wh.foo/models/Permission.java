package com.wh.foo.models;

import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 13:56
 * @Description:
 */
@Entity
@Table(name = "foo_permission")
public class Permission extends BaseEntity{

    /** 删除（0-否， 1-是） */
    private int state = 0;
    /** 名称 */
    private String name;
    /** 代码 */
    private String code;
    /** 对应角色 */
    private List<Role> roles = Lists.newArrayList();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
