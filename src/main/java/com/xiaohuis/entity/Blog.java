package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 博客标题
     */
    private String title;

    /**
     * 文章类型
     */
    private String type;

    /**
     * 博客摘要
     */
    private String summary;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 所属分类id
     */
    private Long classificationId;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 封面地址
     */
    private String pictureUrl;

    /**
     * 是否开启推荐
     */
    private Integer isRecommend;

    /**
     * 是否开启转载声明
     */
    private Integer isReprint;

    /**
     * 是否开启赞赏
     */
    private Integer isAppreciation;

    /**
     * 是否开启评论
     */
    private Integer isComment;

    /**
     * 1.原创；2.转载；3.翻译
     */
    private Integer property;

    /**
     * 状态：0.逻辑删除；1.已发布；2.草稿
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsReprint() {
        return isReprint;
    }

    public void setIsReprint(Integer isReprint) {
        this.isReprint = isReprint;
    }

    public Integer getIsAppreciation() {
        return isAppreciation;
    }

    public void setIsAppreciation(Integer isAppreciation) {
        this.isAppreciation = isAppreciation;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", classificationId=" + classificationId +
                ", views=" + views +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", isRecommend=" + isRecommend +
                ", isReprint=" + isReprint +
                ", isAppreciation=" + isAppreciation +
                ", isComment=" + isComment +
                ", property=" + property +
                ", status=" + status +
                '}';
    }
}
