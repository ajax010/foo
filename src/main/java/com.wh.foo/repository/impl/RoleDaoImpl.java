package com.wh.foo.repository.impl;

import com.wh.foo.repository.custom.RoleDaoCustom;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 角色Dao接口方法扩展实现
 * @Auther: WangHong
 * @Date: 2020/4/10 10:48
 */
public class RoleDaoImpl implements RoleDaoCustom {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void batchSave(Long roleId, String[] permissionIds) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into foo_role_permissions(role_id, permission_id) ");
        sql.append("values(?, ?) ");
        jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String permissionId = permissionIds[i];
                ps.setLong(1, roleId);
                ps.setLong(2, Long.parseLong(permissionId));
            }

            @Override
            public int getBatchSize() {
                return permissionIds.length;
            }
        });
    }

    @Override
    public void removePermissions(Long roleId) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from foo_role_permissions where role_id = ? ");
        jdbcTemplate.update(sql.toString(), new Object[]{roleId});
    }
}
