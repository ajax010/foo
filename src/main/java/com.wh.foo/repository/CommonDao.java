package com.wh.foo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

/**
 * @Description: 封装分页查询接口
 * @Auther: WangHong
 * @Date: 2020/3/28 18:43
 */
@NoRepositoryBean
public interface CommonDao {
    /**
     * 根据JPQL查询分页记录.
     */
    Page<Map<String, Object>> findPageJpql(String jpql, Map<String, Object> params, Pageable pageable);

    /**
     * 根据SQL查询分页记录.
     */
    Page<Map<String, Object>> findPageSql(String sql, Object[] params, Pageable pageable);
}
