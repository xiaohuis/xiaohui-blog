package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Tag;
import com.xiaohuis.pojo.BlogVo;

import java.util.List;

/**
 * <p>
 *  标签服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface TagService {

    /**
     * 查询Tag，并分页显示
     * @return
     */
    PageInfo<Tag> findTagByPage();

    /**
     * 查询所有Tag,不分页
     * @return
     */
    List<Tag> findAllTags();

    /**
     * 添加博客时，需添加或修改相关标签和与博客的对应关系
     * @param blogVo
     */
    void addAndUpdateTags(BlogVo blogVo);

    /**
     * 删除博客时，需修改标签对应的博客数量，和相关标签和与博客的对应关系
     * @param tagList
     */
    void updateTagsBlogNumber(List<Tag> tagList);

    /**
     * 根据tagId，删除tag
     * @param tagId
     * @return
     */
    void deleteTagByTagId(Long tagId);

    /**
     * 把博客更改为作品时，需修改标签对应的博客数量，和相关标签和与博客的对应关系
     * @param oldTags
     * @param blogId
     */
    void updateTagsBlogNumberAndRelation(String oldTags, Long blogId);
}
