package com.xiaohuis.pojo;

import java.io.Serializable;

/**
 * <p> 修改密码时使用 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/1 16:23
 */
public class PasswordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String repassPassword;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 系统取出的验证码
     */
    private String checkCode;

    /**
     * 系统取出的邮箱验证码
     */
    private String emailCode;


    /**
     * 邮箱验证码
     */
    private String userEmailCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepassPassword() {
        return repassPassword;
    }

    public void setRepassPassword(String repassPassword) {
        this.repassPassword = repassPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getUserEmailCode() {
        return userEmailCode;
    }

    public void setUserEmailCode(String userEmailCode) {
        this.userEmailCode = userEmailCode;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", newPassword='" + newPassword + '\'' +
                ", repassPassword='" + repassPassword + '\'' +
                ", email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", emailCode='" + emailCode + '\'' +
                ", userEmailCode='" + userEmailCode + '\'' +
                '}';
    }
}
