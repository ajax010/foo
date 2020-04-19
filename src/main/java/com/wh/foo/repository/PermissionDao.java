package com.wh.foo.repository;

import com.wh.foo.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 权限Dao接口
 * @Auther: WangHong
 * @Date: 2020/4/8 16:54
 */
public interface PermissionDao extends JpaRepository<Permission, Long> {

    /**
     * 根据删除状态查询及父ID权限集合
     *
     * @Param [state， id]
     * @Author WangHong
     * @Date 16:55 2020/4/8
     * @return java.util.List<com.wh.foo.models.Permission>
     **/
    List<Permission> findByStateAndParentId(final int state, final Long id);
}
