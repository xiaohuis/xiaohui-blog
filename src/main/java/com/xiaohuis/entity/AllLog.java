package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 前台日志实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class AllLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法
     */
    private String httpMethod;

    /**
     * 类方法
     */
    private String classMethod;

    /**
     * 接口耗时
     */
    private Long timeCost;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 浏览器标识
     */
    private String userAgent;

    /**
     * 时间
     */
    private Date createTime;

    public AllLog() {
    }

    public AllLog(String ip, String url, String httpMethod, String classMethod, Long timeCost, String os, String browser, String userAgent, Date createTime) {
        this.ip = ip;
        this.url = url;
        this.httpMethod = httpMethod;
        this.classMethod = classMethod;
        this.timeCost = timeCost;
        this.os = os;
        this.browser = browser;
        this.userAgent = userAgent;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AllLog{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", timeCost=" + timeCost +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
