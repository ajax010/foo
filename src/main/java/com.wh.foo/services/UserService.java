package com.wh.foo.services;

import com.wh.foo.core.PasswordHelper;
import com.wh.foo.models.User;
import com.wh.foo.repository.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 14:52
 * @Description: 用户Service
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     * 根据ID变更用户的删除状态
     *
     * @Param [id]
     * @Author WangHong
     * @Date 14:07 2020/4/8
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id){
        if(null == id){
            throw new RuntimeException("必要参数为空！");
        }

        User user = userDao.findByStateAndId(0, id);
        if(null != user){
            user.setState(1);
            userDao.save(user);
        }
    }

    /**
     * 重置用户密码
     *
     * @Param [user]
     * @Author WangHong
     * @Date 14:00 2020/4/8
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(User user){
        user.setPassword(null);
        user = PasswordHelper.encryptPassword(user);
        userDao.save(user);
    }

    /**
     * 根据账号查询未删除用户
     *
     * @Param [state, username]
     * @Author WangHong
     * @Date 9:30 2020/4/8
     * @return com.wh.foo.models.User
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findByStateAndUsername(final int state, final String username){
        return userDao.findByStateAndUsername(state, username);
    }

    /**
     * 根据ID查询未删除的用户信息
     *
     * @Param [state, id]
     * @Author WangHong
     * @Date 16:06 2020/4/7
     * @return com.wh.foo.models.User
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findByStateAndId(final int state, final Long id){
        return userDao.findByStateAndId(state, id);
    }

    /**
     * 保存用户信息
     *
     * @Param [entity]
     * @Author WangHong
     * @Date 16:05 2020/4/7
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void save(User entity){
        if(null == entity.getId()){
            entity = PasswordHelper.encryptPassword(entity);
        }
        userDao.save(entity);
    }

    /**
     * 查询用户信息集合
     *
     * @Param []
     * @Author WangHong
     * @Date 15:19 2020/4/7
     * @return java.util.List<com.wh.foo.models.User>
     **/
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<User> findAll(){
        return userDao.findByState(0);
    }

    /**
     * 分页查询用户信息
     *
     * @Param [searchParams, pageNumber, pageSize]
     * @Author WangHong
     * @Date 18:52 2020/3/28
     * @return org.springframework.data.domain.Page<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<Map<String, Object>> getPage(Map<String, Object> searchParams, final int pageNumber, final int pageSize){
        return userDao.getPage(searchParams, PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 根据用户名查询用户
     *
     * @Param [username]
     * @Author WangHong
     * @Date 14:55 2020/3/27
     * @return com.wh.foo.models.User
     **/
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findUserByName(final String username){
        return userDao.findByUsernameAndState(username, 0);
    }
}
