package com.xiaohuis.dao;

import com.xiaohuis.entity.Blog;
import com.xiaohuis.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

@Mapper
public interface BlogDao {

    /**
     *根据查询blog和对应的classification和user
     * @return
     */
    List<BlogCombination> findBlogCombination();

    /**
     * 根据条件查询RecommendedBlog
     * @param isRecommend
     * @return
     */
    List<RecommendedBlog> findRecommendedBlogByConditions(@Param("isRecommend") int isRecommend);

    /**
     *根据条件联合查询blog和classification和user
     * @param conditions 搜索条件
     * @param status 状态条件
     * @return
     */
    List<FindBlogVo> searchBlogCombinationByConditions(@Param("conditions") String conditions, @Param("status") Integer status);
    /**
     *根据条件联合查询blog和classification和user,返回列表
     * @param blog blog中的某个参数作为条件
     * @return
     */
    List<BlogCombination> findBlogCombinationListByConditions(@Param("blog") Blog blog);

    /**
     *根据条件联合查询blog和classification和user
     * @param blog blog中的某个参数作为条件
     * @return
     */
    BlogCombination findBlogCombinationByConditions(@Param("blog") Blog blog);

    /**
     * 根据tagId联合查询blog和classification和user
     * @param tagId
     * @return
     */
    List<BlogCombination> findBlogCombinationByTagId(@Param("tagId") Long tagId);


    /**
     * 添加博客，并返回自增id
     * @param blogVo
     * @return
     */
    void addBlog(@Param("blogVo") BlogVo blogVo);

    /**
     * 更新博客
     * @param blogVo
     * @return
     */
    void updateBlog(@Param("blogVo") BlogVo blogVo);

    /**
     *更新blog的状态为逻辑删除2
     * @param blogId
     */
    void deleteBlog(@Param("blogId") Long blogId);

    /**
     * 根据时间分组查询博客的年份
     * @return
     */
    List<String> findGroupYear();

    /**
     * 根据年份时间查询博客
     * @param year
     * @return
     */
    List<Blog> findBlogByYear(@Param("year") String year);

    /**
     * 查询博客总数
     * @return
     */
    int findBlogCount();


    /**
     * 查询博客浏览量
     * @param id 博客id
     * @return
     */
    int findBlogViews(@Param("id") Long id);

    /**
     * 查询博客浏览量
     * @param id 博客id
     * @param views 博客浏览量
     */
    void updateBlogViews(@Param("id") Long id, @Param("views") Integer views);
}
