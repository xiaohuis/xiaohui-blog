package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台日志实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    private Long id;

    /**
     * 事件
     */
    private String action;

    /**
     * 数据
     */
    private String data;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作时间
     */
    private Date createdTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", data='" + data + '\'' +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
