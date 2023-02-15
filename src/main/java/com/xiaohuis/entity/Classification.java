package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分类实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID编号
     */

    private Long id;

    /**
     * 分类名称
     */
    private String classificationName;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 博客数量
     */
    private Long blogNumber;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态：0-->逻辑删除，1-->未删除
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getBlogNumber() {
        return blogNumber;
    }

    public void setBlogNumber(Long blogNumber) {
        this.blogNumber = blogNumber;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", classificationName='" +classificationName  + '\'' +
                ", introduction='" + introduction + '\'' +
                ", blogNumber=" + blogNumber +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
