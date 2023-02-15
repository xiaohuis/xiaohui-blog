package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Comment;
import com.xiaohuis.pojo.PublishedComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface CommentService {

    /**
     * 根据blogId查询评论
     * @param blogId
     * @return
     */
    Map<String, List<Comment>> findCommentsByBlogId(@Param("blog") Long blogId);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    void insertComment(Comment comment);

    /**
     * 分页查找评论
     * @param pageNum
     * @return
     */
    PageInfo<Comment> findCommentsByPaging(int pageNum);

    /**
     * 分页查找评论和博客标题
     * @param pageNum
     * @return
     */
    PageInfo<PublishedComment> findCommentsAndBlogTitleByPaging(Integer pageNum);

    /**
     * 更改评论状态为1，通过审核
     * @param id
     * @return
     */
    void updateCommentStaticById(Long id);

    /**
     * 更改评论状态为0，实现逻辑删除
     * @param id
     * @return
     */
    void deleteCommentById(Long id);

    /**
     * 根据blogId更改评论状态为0，实现逻辑删除
     * @param blogId
     * @return
     */
    void deleteCommentByBlogId(Long blogId);
}
