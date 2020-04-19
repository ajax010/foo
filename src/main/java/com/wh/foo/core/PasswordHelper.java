package com.wh.foo.core;

import com.wh.foo.models.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Auther: WangHong
 * @Date: 2020/3/27 14:47
 * @Description: 密码加密
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    /**
     * 生成密码
     *
     * @Param [user]
     * @Author WangHong
     * @Date 11:28 2020/4/8
     * @return com.wh.foo.models.User
     **/
    public static User encryptPassword(User user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword("000000");
        }
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
        return user;
    }

//    public static void main(String args[]){
//        PasswordHelper passwordHelper = new PasswordHelper();
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("admin");
//        passwordHelper.encryptPassword(user);
//    }
}
