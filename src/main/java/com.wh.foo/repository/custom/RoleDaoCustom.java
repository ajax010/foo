package com.wh.foo.repository.custom;

/**
 * @Description: 角色Dao接口方法扩展
 * @Auther: WangHong
 * @Date: 2020/4/10 10:48
 */
public interface RoleDaoCustom{

    /**
     * 保存角色权限关联信息
     *
     * @Param [roleId, permissionIds]
     * @Author WangHong
     * @Date 8:11 2020/4/12
     * @return void
     **/
    void batchSave(final Long roleId, String[] permissionIds);

    /**
     * 移除角色相关权限信息
     *
     * @Param [roleId]
     * @Author WangHong
     * @Date 8:08 2020/4/12
     * @return void
     **/
    void removePermissions(final Long roleId);
}
