package com.wh.foo.services;

import com.wh.foo.models.Permission;
import com.wh.foo.models.Role;
import com.wh.foo.repository.PermissionDao;
import com.wh.foo.repository.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 角色Service
 * @Auther: WangHong
 * @Date: 2020/4/8 16:13
 */
@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;

    /**
     * 查询全部未删除权限
     *
     * @Param []
     * @Author WangHong
     * @Date 16:55 2020/4/8
     * @return java.util.List<com.wh.foo.models.Permission>
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Permission> findPermissionAll(){
        return permissionDao.findByState(0);
    }

    /**
     * 根据ID查询角色
     *
     * @Param [id]
     * @Author WangHong
     * @Date 16:48 2020/4/8
     * @return com.wh.foo.models.Role
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Role findOne(final Long id){
        return roleDao.findByStateAndId(0, id);
    }

    /**
     * 查询全部未删除角色
     *
     * @Param []
     * @Author WangHong
     * @Date 16:16 2020/4/8
     * @return java.util.List<com.wh.foo.models.Role>
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Role> findAll(){
        return roleDao.findByState(0);
    }
}
