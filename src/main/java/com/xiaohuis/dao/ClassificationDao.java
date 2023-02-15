package com.xiaohuis.dao;

import com.xiaohuis.entity.Classification;
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
public interface ClassificationDao {

    /**
     * 查询所有Classification
     * @return
     */
    List<Classification> findAllClassification();

    /**
     *根据单个主键查询Classification
     * @param classificationId
     * @return
     */
    Classification findClassificationById(@Param("classificationId") Long classificationId);

    /**
     *根据单个主键修改Classification的BlogNumber
     * @param classification
     * @return
     */
    void updateClassificationBlogNumberByClassificationId(@Param("classification") Classification classification);

    /**
     * 根据单个主键修改Classification
     * @param classification
     */
    void updateClassificationByClassificationId(@Param("classification") Classification classification);

    /**
     * 添加Classification
     * @param classification
     */
    void saveClassification(@Param("classification") Classification classification);

    /**
     * 根据单个主键更新Classification的static为0，进行逻辑删除
     * @param classificationId
     */
    void deleteClassificationByClassificationId(@Param("classificationId") Long classificationId);
}
