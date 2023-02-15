package com.xiaohuis.dao;

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
public interface MenuDao {


    /**
     * 根据roleId查询相应的menu
     * @param userId
     * @return
     */
    List<String> findPermsByUserId(@Param("userId") Long userId);

}
