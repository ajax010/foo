package com.wh.foo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @Description: 封装分页查询接口实现类
 * @Auther: WangHong
 * @Date: 2020/3/28 18:43
 */
@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 根据JPQL查询分页记录.
     */
    @Override
    public Page<Map<String, Object>> findPageJpql(String jpql, Map<String, Object> params, Pageable pageable) {

        Query query = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);

        if(!params.isEmpty() && params.size() > 0){
            for(Map.Entry<String, Object> entry:params.entrySet()){
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }

        List list = query.getResultList();
        long total = 0;
        if(!list.isEmpty() && list.size() > 0){
            total = list.size();
        }

        query.setFirstResult(Integer.parseInt(String.valueOf(pageable.getOffset())));
        query.setMaxResults(pageable.getPageSize());

        List content = query.getResultList();

        return new PageImpl(content, pageable, total);
    }

    /**
     * 根据SQL查询分页记录.
     */
    @Override
    public Page<Map<String, Object>> findPageSql(String sql, Object[] params, Pageable pageable) {

        String totalSql = String.format("select count(*) from (%s) tt", sql);
        Number number = jdbcTemplate.queryForObject(totalSql, params, Long.class);
        long total = (number != null ? number.longValue() : 0);

        int start = pageable.getPageNumber() * pageable.getPageSize();

        sql = String.format("%s limit %d, %d", sql, start, pageable.getPageSize());

        List<Map<String, Object>> content = jdbcTemplate.queryForList(sql, params);

        return new PageImpl<Map<String, Object>>(content, pageable, total);
    }
}
