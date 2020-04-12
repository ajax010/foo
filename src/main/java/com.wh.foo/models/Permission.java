package com.wh.foo.models;

import com.google.common.collect.Lists;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
    /** 所属栏目名称 */
    private String groupName;
    /** 所属权限组 */
    private Permission parent;
    /** 权限组下权限 */
    private List<Permission> permissions = Lists.newArrayList();
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }


    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Where(clause="state=0")
    @OrderBy("id ASC")
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
