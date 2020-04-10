package com.wh.foo.repository.impl;

import com.wh.foo.repository.CommonDao;
import com.wh.foo.repository.custom.UserDaoCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户Dao接口扩展实现类
 * @Auther: WangHong
 * @Date: 2020/3/28 18:43
 */
public class UserDaoImpl implements UserDaoCustom {

    @Resource
    private CommonDao commonDao;

    @Override
    public Page<Map<String, Object>> getPage(Map<String, Object> searchParams, PageRequest pageRequest) {
        List<Object> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select a.id, a.username, a.nickname, date_format(a.create_date, '%Y-%m-%d %H:%i') as createDate ");
        sql.append("from foo_user a where a.state = 0 ");
        if(null != searchParams && !searchParams.isEmpty()){
            if(null != searchParams.get("username")){
                sql.append("and a.username like ? ");
                list.add("%" + searchParams.get("username") + "%");
            }

            if(null != searchParams.get("nickname")){
                sql.append("and a.nickname like ? ");
                list.add("%" + searchParams.get("nickname") + "%");
            }
        }
        return commonDao.findPageSql(sql.toString(), list.toArray(), pageRequest);
    }
}
