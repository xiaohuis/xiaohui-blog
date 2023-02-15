package com.xiaohuis.constant;

/**
 * <p> 系统常量 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/2 22:38
 */
public class SystemConstant {

    /**
     * 验证码随机数
     */
    public static final String VERIFY_CODES = "0123456789";

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "123456789xiaohui";

    /**
     * 用户登录存进redis的key
     */
    public static final String LOGIN_REDIS_KEY = "login:";


    /**
     * 用户登录存进session的key
     */
    public static final String LOGIN_SESSION_KEY = "loginUser";

    /**
     * 记住密码的cookie索引
     */
    public static final String USER_LOGIN_COOKIE = "token";


    public static final String PASSWORD_SALT = "xiaohui";

}
