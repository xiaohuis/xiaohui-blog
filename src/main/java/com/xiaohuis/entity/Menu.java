package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限菜单实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * MenuId
     */
    private Long id;

    /**
     * 权限名
     */
    private String menuName;

    /**
     * 权限标识符
     */
    private String perms;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 描述
     */
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creat_time) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", menuName=" + menuName +
        ", perms=" + perms +
        ", path=" + path +
        ", component=" + component +
        ", status=" + status +
        ", icon=" + icon +
        ", creatTime=" + creatTime +
        ", updateTime=" + updateTime +
        ", description=" + description +
        "}";
    }
}
