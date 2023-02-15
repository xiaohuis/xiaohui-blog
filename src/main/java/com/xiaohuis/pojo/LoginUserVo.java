package com.xiaohuis.pojo;

import java.io.Serializable;

/**
 * <p>
 * 登录接收前端数据使用
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class LoginUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 记住密码
     */
    private String rememberMe;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
