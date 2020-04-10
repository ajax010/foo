package com.wh.foo.services;

import com.google.common.collect.Lists;
import com.wh.foo.models.Permission;
import com.wh.foo.models.Role;
import com.wh.foo.repository.PermissionDao;
import com.wh.foo.repository.RoleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

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
     * 分页查询角色信息
     *
     * @Param [searchParams, pageNumber, pageSize]
     * @Author WangHong
     * @Date 10:54 2020/4/10
     * @return org.springframework.data.domain.Page<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<Role> findPage(Map<String, Object> searchParams, final int pageNumber, final int pageSize){
        return roleDao.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(cb.equal(root.get("state"), 0));

            if(null != searchParams && !searchParams.isEmpty()){
                if(null != searchParams.get("name") && StringUtils.isNotBlank(searchParams.get("name").toString())){
                    predicates.add(cb.like(root.get("name"), "%" + searchParams.get("name") + "%"));
                }
            }

            // 将所有条件用 and 联合起来
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        }, PageRequest.of(pageNumber, pageSize));
    }

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
