package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Blog;
import com.xiaohuis.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  博客接口
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface BlogService {

    /**
     * 首页查询blog和根据查出的blog信息中的classificationId和userId，查询出对应的classification和user，并使用插件分页显示
     * @param pageNum 页码数
     * @return
     */

    PageInfo<BlogCombination> findBlogCombinationByPaging(Integer pageNum);


    /**
     * 首页查询是否需要推荐的blog，并分页显示
     * @param pageNum 页码数
     * @param isRecommend 是否推荐
     * @return
     */
    PageInfo<RecommendedBlog> findBlogByRecommendAndPaging(Integer pageNum,Integer isRecommend);

    /**
     *实现search功能，查询blog和根据查出的blog信息中的classificationId和userId，查询出对应的classification和user，并使用插件分页显示
     * @param conditions 搜索条件
     * @param pageNum 页码
     * @param status 博客状态
     * @return
     */
    PageInfo<FindBlogVo> searchBlogByConditionsAndPaging(String conditions, Integer pageNum, Integer status);

    /**
     * 实现blog详情功能，根据blogId联合查询blog和classification和use和tag
     * @param id bolgId条件
     * @return
     */
    BlogCombination findBlogCombinationByBlogId(Long id);

    /**
     * 根据tagId联合查询blog和classification和use和tag，并且返回BlogCombination，并使用插件分页显示
     * @param tagId
     * @param pageNum
     * @return
     */
    PageInfo<BlogCombination> findBlogCombinationByTagIdAndPaging(Long tagId, int pageNum);

    /**
     * 根据blog中的classificationId联合查询blog和classification和use和tag，并且返回BlogCombination，并使用插件分页显示
     * @param blog
     * @param pageNum
     * @return
     */
    PageInfo<BlogCombination> findBlogCombinationByTypeIdAndPaging(Blog blog, int pageNum);

    /**
     * 添加blog
     * @param blogVo
     * @return
     */
    void addBlog(BlogVo blogVo);
    /**
     * 修改blog
     * @param blogVo
     * @return
     */
    void updateBlog(BlogVo blogVo);

    /**
     * 删除blog以及对应的tag和classifications
     * @param blogId
     * @return
     */
    void deleteBlog(Long blogId);

    /**
     * 根据时间分组查询博客，实现归档功能
     * @return
     */
    Map<String, List<Blog>> findBlogByCreatTime();

    /**
     * 查询博客总数
     * @return
     */
    int findBlogCount();

    /**
     * 游客查询博客详情时，添加博客浏览量
     * @param id 博客id
     * @return
     */
    void updateBlogViews(Long id);
}
