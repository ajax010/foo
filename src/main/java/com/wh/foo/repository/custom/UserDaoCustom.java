package com.wh.foo.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * @Description: 用户Dao接口扩展
 * @Auther: WangHong
 * @Date: 2020/3/28 18:44
 */
public interface UserDaoCustom {

    /**
     * 清空用户角色
     *
     * @Param [userId]
     * @Author WangHong
     * @Date 10:30 2020/4/12
     * @return void
     **/
    void removeRole(final Long userId);

    /**
     * 保存用户角色关系
     *
     * @Param [userId, roleIds]
     * @Author WangHong
     * @Date 10:27 2020/4/12
     * @return void
     **/
    void saveRole(final Long userId, final String[] roleIds);

    /**
     * 分页查询用户列表
     *
     * @Param [searchParams, pageRequest]
     * @Author WangHong
     * @Date 18:44 2020/3/28
     * @return org.springframework.data.domain.Page<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    Page<Map<String, Object>> getPage(Map<String, Object> searchParams, final PageRequest pageRequest);
}
