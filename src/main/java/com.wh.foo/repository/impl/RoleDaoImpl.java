package com.wh.foo.repository.impl;

import com.wh.foo.repository.CommonDao;
import com.wh.foo.repository.custom.RoleDaoCustom;

import javax.annotation.Resource;

/**
 * @Description: 角色Dao接口方法扩展实现
 * @Auther: WangHong
 * @Date: 2020/4/10 10:48
 */
public class RoleDaoImpl implements RoleDaoCustom {

    @Resource
    private CommonDao commonDao;
}
