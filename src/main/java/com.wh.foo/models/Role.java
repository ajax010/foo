package com.wh.foo.models;

import com.google.common.collect.Lists;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 13:44
 * @Description: 角色
 */
@Entity
@Table(name = "foo_role")
public class Role extends BaseEntity{

    /** 删除(0-否， 1-是) */
    private int state = 0;
    /** 角色名称 */
    private String name;
    /** 描述 */
    private String remark;
    /** 对应用户 */
    private List<User> users = Lists.newArrayList();
    /** 对应资源 */
    private List<Permission> permissions = Lists.newArrayList();

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

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "foo_role_permissions",
            joinColumns = {@JoinColumn(name="role_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="permissions_id",referencedColumnName="id")})
    @Where(clause = "state=0")
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public String getPermissionNameStr(){
        String permissionNameStr = null;
        if(this.getPermissions().size() > 0){
            StringBuffer sb = new StringBuffer();
            for(Permission p : this.getPermissions()){
                sb.append(p.getName() + ",");
            }

            permissionNameStr = sb.toString();
            permissionNameStr = permissionNameStr.length() > 20 ? permissionNameStr.substring(0, 20) + "..." : permissionNameStr.substring(0, permissionNameStr.length() - 1);
        }
        return permissionNameStr;
    }
}
