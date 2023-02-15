package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */

    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", blogNumber=" + blogNumber +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
