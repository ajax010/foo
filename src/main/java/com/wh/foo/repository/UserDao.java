package com.wh.foo.repository;

import com.wh.foo.models.User;
import com.wh.foo.repository.custom.UserDaoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 用户Dao
 *
 * @Auther: WangHong
 * @Date: 2020/3/27 14:53
 */
public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom {

    /**
     * 根据账号查询未删除用户
     *
     * @Param [state, username]
     * @Author WangHong
     * @Date 9:30 2020/4/8
     * @return com.wh.foo.models.User
     **/
    User findByStateAndUsername(final int state, final String username);

    /**
     * 根据ID查询未删除的用户信息
     *
     * @Param [state, id]
     * @Author WangHong
     * @Date 16:06 2020/4/7
     * @return com.wh.foo.models.User
     **/
    User findByStateAndId(final int state, final Long id);

    /**
     * 查询全部删除状态正常的用户
     *
     * @Param [state]
     * @Author WangHong
     * @Date 15:20 2020/4/7
     * @return java.util.List<com.wh.foo.models.User>
     **/
    List<User> findByState(final int state);

    /**
     * 根据账号查询用户信息
     *
     * @Param [username, state]
     * @Author WangHong
     * @Date 18:43 2020/3/28
     * @return com.wh.foo.models.User
     **/
    User findByUsernameAndState(final String username, final int state);
}
