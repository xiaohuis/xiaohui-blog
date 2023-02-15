package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Classification;
import com.xiaohuis.pojo.BlogVo;

import java.util.List;

/**
 * <p>
 *  博客分类服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface ClassificationService {

    /**
     * 查询classification，并分页显示
     * @return
     */
    PageInfo<Classification> findClassificationByPaging();

    /**
     * 查询所有classification，不分页
     * @return
     */
    List<Classification> findAllClassification();

    /**
     * 新增blog时，修改Classification对应的博客数量
     * @param blogVo
     */
    void updateClassificationBlogNumber(BlogVo blogVo);

    /**
     * 删除blog时，修改Classification对应的博客数量
     * @param classificationId
     */
    void updateClassificationBlogNumber(Long classificationId);

    /**
     * 修改Classification
     * @param classification
     * @return
     */
    void updateClassification(Classification classification);

    /**
     * 新增Classification
     * @param classification
     * @return
     */
    void saveClassification(Classification classification);
    /**
     * 根据classificationId，删除Classification
     * @param classificationId
     * @return
     */
    void deleteClassificationByClassificationId(Long classificationId);
}
