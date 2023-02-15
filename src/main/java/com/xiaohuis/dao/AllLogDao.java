package com.xiaohuis.dao;

import com.xiaohuis.entity.AllLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  前台日志Mapper 接口
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

@Mapper
public interface AllLogDao {

    /**
     * 保存前台日志
     * @param allLog
     */
    void saveAllLog(@Param("allLog") AllLog allLog);

}
