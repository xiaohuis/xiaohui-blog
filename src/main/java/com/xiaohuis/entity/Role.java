package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date creatTime;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role_name) {
        this.roleName = role_name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
        "id=" + id +
        ", role_name=" + roleName +
        ", role_key=" + roleKey +
        ", status=" + status +
        ", creat_time=" + creatTime +
        ", description=" + description +
        "}";
    }
}
