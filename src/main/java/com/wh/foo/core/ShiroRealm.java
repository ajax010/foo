package com.wh.foo.core;

import com.wh.foo.models.Permission;
import com.wh.foo.models.Role;
import com.wh.foo.models.User;
import com.wh.foo.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 14:50
 * @Description: 自定义的Realm
 * 具体来说就是实现doGetAuthenticationInfo和doGetAuthorizationInfo，
 * 这两个方法前者负责登陆认证后者负责提供一个权限信息。
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        if(null == principal){
            return null;
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findByStateAndId(0, principal.getId());
        for (Role role : user.getRoles()) {
            authorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getCode());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findUserByName(username);
        if (user == null) {
            return null;
        }

        Principal principal = new Principal(user.getId(), user.getUsername(), user.getNickname(), new Date());
        return new SimpleAuthenticationInfo(principal, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
    }


    public void clearAuthz(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    public class Principal{

        /** 用户Id */
        private Long id;
        /** 账号 */
        private String username;
        /** 昵称 */
        private String nickname;
        /** 登录时间 */
        private Date time;

        public Principal(Long id, String username, String nickname, Date time){
            this.id = id;
            this.username = username;
            this.nickname = nickname;
            this.time = time;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }
}
