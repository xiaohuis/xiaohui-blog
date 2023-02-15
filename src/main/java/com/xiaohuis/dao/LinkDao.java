package com.xiaohuis.dao;

import com.xiaohuis.entity.Link;
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
public interface LinkDao {

    /**
     * 查询链接总数
     * @return
     */
    int findLinkCount();

    /**
     * 查询所有链接
     * @return
     */
    List<Link> findAllLink();

    /**
     * 保存link
     * @param link
     */
    void saveLink(@Param("link") Link link);

    /**
     * 更新link
     * @param link
     */
    void updateLink(@Param("link") Link link);

    /**
     * 根据id更新链接的状态为0，实现逻辑删除
     * @param id
     */
    void updateLinkStatusById(@Param("id") Long id);
}
