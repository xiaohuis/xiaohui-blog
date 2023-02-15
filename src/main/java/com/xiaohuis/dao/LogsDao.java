package com.xiaohuis.dao;

import com.xiaohuis.entity.Logs;
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
public interface LogsDao{

    /**
     * 查询系统后台日志
     * @return
     */
    List<Logs> findLogs();

    /**
     * 保存后台日志
     * @param logs
     */
    void saveLogs(@Param("logs") Logs logs);
}
