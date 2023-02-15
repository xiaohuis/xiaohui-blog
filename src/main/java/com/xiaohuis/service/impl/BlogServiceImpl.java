package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.dao.BlogDao;
import com.xiaohuis.entity.Blog;
import com.xiaohuis.entity.Tag;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.pojo.*;
import com.xiaohuis.service.BlogService;
import com.xiaohuis.service.ClassificationService;
import com.xiaohuis.service.CommentService;
import com.xiaohuis.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;


    @Autowired
    private TagService tagService;

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private CommentService commentService;

    @Override
    public PageInfo<BlogCombination> findBlogCombinationByPaging(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum,5);
        //查询blog和根据查出的blog信息中的classificationId和userId，查询出对应的classification和user
        List<BlogCombination> blogAndClassificationAndUser = blogDao.findBlogCombination();
        //获取分页相关数据
        PageInfo<BlogCombination> blogCombinations = new PageInfo<>(blogAndClassificationAndUser,3);
        return blogCombinations;
    }


    @Override
    public PageInfo<RecommendedBlog> findBlogByRecommendAndPaging(Integer pageNum,Integer isRecommend) {
        //开启分页功能
        PageHelper.startPage(pageNum,6);
        //获取分页相关数据
        PageInfo<RecommendedBlog> recommendedBlogs = new PageInfo<>(blogDao.findRecommendedBlogByConditions(isRecommend));
        return recommendedBlogs;
    }

    @Override
    public PageInfo<FindBlogVo> searchBlogByConditionsAndPaging(String conditions, Integer pageNum, Integer status) {
        //开启分页功能
        PageHelper.startPage(pageNum,6);
        //获取分页相关数据
        PageInfo<FindBlogVo> searchBlogs = new PageInfo<>(blogDao.searchBlogCombinationByConditions(conditions,status),3);
        return searchBlogs;
    }

    @Override
    public BlogCombination findBlogCombinationByBlogId(Long id) {
        if(null == id){
            throw  new BusinessException(Code.FIND_ERR_CODE, Message.PARAM_IS_EMPTY);
        }
        Blog blog = new Blog();
        blog.setId(id);
        blog.setStatus(3);
        BlogCombination blogCombination = blogDao.findBlogCombinationByConditions(blog);
        String tags = "";
        for(Tag tag : blogCombination.getTagList()){
            tags += tag.getTagName() + ",";
        }
        blogCombination.setTags(tags);
        return blogCombination;
    }

    @Override
    public PageInfo<BlogCombination> findBlogCombinationByTagIdAndPaging(Long tagId, int pageNum) {
        if(null == tagId){
            throw  new BusinessException(Code.FIND_ERR_CODE, Message.PARAM_IS_EMPTY);
        }
        //开启分页功能
        PageHelper.startPage(pageNum,6);
        //获取分页相关数据
        PageInfo<BlogCombination> blogCombinationPageInfo = new PageInfo<>(blogDao.findBlogCombinationByTagId(tagId), 2);
        return blogCombinationPageInfo;
    }

    @Override
    public PageInfo<BlogCombination> findBlogCombinationByTypeIdAndPaging(Blog blog, int pageNum) {
        if(null == blog){
            throw  new BusinessException(Code.FIND_ERR_CODE, Message.PARAM_IS_EMPTY);
        }
        //开启分页功能
        PageHelper.startPage(pageNum,6);
        //获取分页相关数据
        PageInfo<BlogCombination> blogCombinationPageInfo = new PageInfo<>(blogDao.findBlogCombinationListByConditions(blog));
        return blogCombinationPageInfo;
    }

    @Override
    public void addBlog(BlogVo blogVo) {
        //判空
        if(null == blogVo || StringUtils.isBlank(blogVo.getSummary()) || StringUtils.isBlank(blogVo.getPictureUrl())
                || StringUtils.isBlank(blogVo.getContent()) || StringUtils.isBlank(blogVo.getTitle())){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }

        //格式验证
        if(blogVo.getTitle().length() > OtherCommonConstants.MAX_TITLE_COUNT){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.TITLE_IS_TOO_LONG);
        }

        if(blogVo.getContent().length() > OtherCommonConstants.MAX_CONTENT_COUNT){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.CONTENT_IS_TOO_LONG);
        }

        //如果为作品，则需把分类，标签和评论去掉
        if(OtherCommonConstants.BLOG_TYPE.equals(blogVo.getType())){
            blogVo.setTags(null);
            blogVo.setClassificationId(null);
            blogVo.setIsComment(0);
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long id = loginUser.getUser().getId();
        blogVo.setUserId(id);
        blogVo.setCreatTime(new Date());

        blogDao.addBlog(blogVo);
        if(blogVo.getClassificationId() != null ){
            classificationService.updateClassificationBlogNumber(blogVo);
        }
        if(blogVo.getTags() != null || !"".equals(blogVo.getTags())){
            tagService.addAndUpdateTags(blogVo);
        }
    }

    @Override
    public void updateBlog(BlogVo blogVo) {
        //判空
        if(blogVo == null || StringUtils.isBlank(blogVo.getSummary()) || StringUtils.isBlank(blogVo.getPictureUrl())
                || StringUtils.isBlank(blogVo.getContent()) || StringUtils.isBlank(blogVo.getTitle())){
            throw  new BusinessException(Code.UPDATE_SUCCESS_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }

        //格式验证
        if(blogVo.getTitle().length() > OtherCommonConstants.MAX_TITLE_COUNT){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.TITLE_IS_TOO_LONG);
        }

        if(blogVo.getContent().length() > OtherCommonConstants.MAX_CONTENT_COUNT){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.CONTENT_IS_TOO_LONG);
        }

        //如果为作品，则需把分类，标签和评论去掉
        if(OtherCommonConstants.BLOG_TYPE.equals(blogVo.getType())){
            //如果是博客转为作品，会有旧的分类和标签，需把对应的博客数量减少，对应关系删除，如果原本就是作品，则无需
            if(blogVo.getOldClassificationId() != null){
                classificationService.updateClassificationBlogNumber(blogVo.getOldClassificationId());
            }
            if(StringUtils.isNotBlank(blogVo.getOldTags())){
                tagService.updateTagsBlogNumberAndRelation(blogVo.getOldTags(),blogVo.getId());
            }
            blogVo.setClassificationId(null);
            blogVo.setTags(null);
            blogVo.setIsComment(0);
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long id = loginUser.getUser().getId();
        blogVo.setUserId(id);
        blogVo.setUpdateTime(new Date());

        blogDao.updateBlog(blogVo);

        //作品更新为博客时，则无oldClassificationId，则需更新博客数量
        if(blogVo.getClassificationId() != null && null == blogVo.getOldClassificationId()){
            classificationService.updateClassificationBlogNumber(blogVo);
        }

        //原本为博客时，则有oldClassificationId，如果更新前和更新后的分类相同，则无需更新博客数量
        if(blogVo.getClassificationId() != null && blogVo.getOldClassificationId() != null && !blogVo.getClassificationId().equals(blogVo.getOldClassificationId())){
            classificationService.updateClassificationBlogNumber(blogVo);
        }
        if(blogVo.getTags() != null){
            tagService.addAndUpdateTags(blogVo);
        }
    }

    @Override
    public void deleteBlog(Long blogId) {

        if(blogId == null){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        Blog blog1 = new Blog();
        blog1.setId(blogId);
        blog1.setStatus(3);
        BlogCombination blogCombinationByConditions = blogDao.findBlogCombinationByConditions(blog1);
        if(blogCombinationByConditions == null){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        blogDao.deleteBlog(blogId);
        if(!OtherCommonConstants.BLOG_TYPE.equals(blogCombinationByConditions.getType())){
            classificationService.updateClassificationBlogNumber(blogCombinationByConditions.getClassificationId());
            tagService.updateTagsBlogNumber(blogCombinationByConditions.getTagList());
            commentService.deleteCommentByBlogId(blogId);
        }
    }

    @Override
    public Map<String, List<Blog>> findBlogByCreatTime() {

        List<String> years = blogDao.findGroupYear();
        Map<String, List<Blog>> blogMap = new HashMap<>();
        for (String year : years){
           blogMap.put(year,blogDao.findBlogByYear(year));
        }
        return blogMap;
    }

    @Override
    public int findBlogCount() {
        return blogDao.findBlogCount();
    }

    @Override
    public void updateBlogViews(Long id) {
        int views = blogDao.findBlogViews(id);
        blogDao.updateBlogViews(id, views+1);
    }
}
