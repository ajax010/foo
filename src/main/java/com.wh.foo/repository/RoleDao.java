package com.wh.foo.repository;

import com.wh.foo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 角色Dao接口
 * @Auther: WangHong
 * @Date: 2020/4/8 16:15
 */
public interface RoleDao extends JpaRepository<Role, Long> {

    /**
     * 根据ID查询角色
     *
     * @Param [state, id]
     * @Author WangHong
     * @Date 16:48 2020/4/8
     * @return com.wh.foo.models.Role
     **/
    Role findByStateAndId(final int state, final Long id);

    /**
     * 查询全部未删除角色
     *
     * @Param [state]
     * @Author WangHong
     * @Date 16:17 2020/4/8
     * @return java.util.List<com.wh.foo.models.Role>
     **/
    List<Role> findByState(final int state);
}
