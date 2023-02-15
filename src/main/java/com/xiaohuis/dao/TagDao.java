package com.xiaohuis.dao;

import com.xiaohuis.entity.Tag;
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
public interface TagDao {

    /**
     *查询所有tag
     * @return
     */
    List<Tag> findAllTag();

    /**
     * 根据blogId查询tag
     * @param blogId
     * @return
     */
    List<Tag> findTagByBlogId(@Param("blogId") Long blogId);

    /**
     * 添加tag，并返回对应的自增id
     * @param tag
     */
    void addTags(@Param("tag") Tag tag);

    /**
     *根据tagName查找tag
     * @param tagName
     * @return
     */
    Tag findTagByTagName(@Param("tagName") String tagName);

    /**
     * 根据tagId更新tag
     * @param tag
     */
    void updateTagByTagId(@Param("tag") Tag tag);

    /**
     *查询tag和blog的对应关系的id
     * @param blogId
     * @param tagId
     * @return
     */
    Long findTagAndBlogByTagIdAndBlogId(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

    /**
     *添加tag和blog的对应关系
     * @param blogId
     * @param tagId
     */
    void addTagAndBlog(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

    /**
     *删除tag和blog的对应关系
     * @param blogId
     * @param tagId
     */
    void deleteTagAndBlog(@Param("blogId") Long blogId,@Param("tagId") Long tagId);

    /**
     * 根据tagId查找tag
     * @param tagId
     * @return
     */
    Tag findTagByTagId(@Param("tagId") Long tagId);

    /**
     * 根据单个主键更新tag的static为0，进行逻辑删除
     * @param tagId
     */
    void deleteTagByTagId(@Param("tagId") Long tagId);
}
