package com.xiaohuis.dao;

import com.xiaohuis.entity.Comment;
import com.xiaohuis.pojo.PublishedComment;
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
public interface CommentDao {


    /**
     * 根据blogId查询父评论
     * @param blogId
     * @return
     */
    List<Comment> findParentsCommentByBlogId(@Param("blogId") Long blogId);

    /**
     * 根据blogId查询子评论
     * @param blogId
     * @return
     */
    List<Comment> findSonsCommentByBlogId(@Param("blogId") Long blogId);

    /**
     * 插入评论
     * @param comment
     * @return
     */
    int insertComment(@Param("comment") Comment comment);

    /**
     * 查找所有评论
     * @return
     */
    List<Comment> findCommentByConditions();

    /**
     * 查找所有评论和对应的博客标题
     * @return
     */
    List<PublishedComment> findCommentsAndBlogTitle();

    /**
     * 根据id更新状态
     * @param commentId
     * @param status
     */
    void updateCommentStaticById(@Param("id") Long commentId,@Param("status") Integer status);

    /**
     * 根据blogId更新状态
     * @param blogId
     */
    void updateCommentStaticByBlogId(@Param("blogId") Long blogId);
}
