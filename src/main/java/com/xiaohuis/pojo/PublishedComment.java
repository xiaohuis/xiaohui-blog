package com.xiaohuis.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 发表评论时使用
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class PublishedComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 评论时间
     */
    private Date creatTime;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 状态：0-->逻辑删除，1-->未删除且审核通过, 2-->未审核
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", blogId=" + blogId +
                ", title='" + title + '\'' +
                ", creatTime=" + creatTime +
                ", isAdmin=" + isAdmin +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                '}';
    }
}
