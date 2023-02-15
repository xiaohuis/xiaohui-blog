package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 友链实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 简单描述
     */
    private String describe;

    /**
     * 友情链接
     */
    private String link;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private int sort;


    private int status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", describe='" + describe + '\'' +
                ", link='" + link + '\'' +
                ", createTime=" + createTime +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}
