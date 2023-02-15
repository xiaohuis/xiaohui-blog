package com.xiaohuis.pojo;

/**
 * <p> 推荐的博客实体类 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/11/25 23:48
 */
public class RecommendedBlog {

    /**
     * 博客id
     */
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客浏览量
     */
    private Integer views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "RecommendedBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", views=" + views +
                '}';
    }
}
