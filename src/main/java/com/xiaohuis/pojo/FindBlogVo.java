package com.xiaohuis.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 搜索查询博客时使用
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class FindBlogVo implements Serializable {

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
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String icon;

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
     * 所属分类名字
     */
    private String classificationName;

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

    public FindBlogVo() {
    }

    public FindBlogVo(BlogCombination blogCombination) {
        this.id = blogCombination.getId();
        this.userId = blogCombination.getUser().getId();
        this.nickname = blogCombination.getUser().getNickname();
        this.icon = blogCombination.getUser().getIcon();
        this.title = blogCombination.getTitle();
        this.type = blogCombination.getType();
        this.summary = blogCombination.getSummary();
        this.content = blogCombination.getContent();
        this.creatTime = blogCombination.getCreatTime();
        this.updateTime = blogCombination.getUpdateTime();
        this.classificationId = blogCombination.getClassification().getId();
        this.classificationName = blogCombination.getClassification().getClassificationName();
        this.views = blogCombination.getViews();
        this.pictureUrl = blogCombination.getPictureUrl();
        this.isRecommend = blogCombination.getIsRecommend();
        this.isReprint = blogCombination.getIsReprint();
        this.isAppreciation = blogCombination.getIsAppreciation();
        this.isComment = blogCombination.getIsComment();
        this.property = blogCombination.getProperty();
        this.status = blogCombination.getStatus();
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
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
        return "SearchBlog{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", classificationId=" + classificationId +
                ", classificationName='" + classificationName + '\'' +
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
