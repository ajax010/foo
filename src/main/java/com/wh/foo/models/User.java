package com.wh.foo.models;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 13:40
 * @Description:
 */
@Entity
@Table(name = "foo_user")
public class User extends BaseEntity{

    /** 删除（0-否， 1-是） */
    private int state = 0;
    /** 账号 */
    private String username;
    /** 密码 */
    private String password;
    /** 加密盐 */
    private String salt;
    /** 昵称 */
    private String nickname;
    /** 对应角色 */
    private List<Role> roles = Lists.newArrayList();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Transient
    public String getCredentialsSalt() {
        return username + salt + salt;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "foo_user_role",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName="id")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
