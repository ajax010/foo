package com.wh.foo.services;

import com.wh.foo.core.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

/**
 * @Description: service基类
 * @Auther: WangHong
 * @Date: 2020/4/12 10:33
 */
public class BaseService {

    /**
     * 更新当前登录用户权限信息
     *
     * @Param []
     * @Author WangHong
     * @Date 10:34 2020/4/12
     * @return void
     **/
    public void clearCacheAuth() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm realm = (ShiroRealm) rsm.getRealms().iterator().next();
        realm.clearAuthz();
    }
}
