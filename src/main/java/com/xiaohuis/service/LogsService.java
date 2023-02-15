package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Logs;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface LogsService{

    /**
     * 分页查询系统后台日志
     * @param pageNum
     * @return
     */
    PageInfo<Logs> findLogsByPaging(int pageNum);


    /**
     * 添加后台日志
     * @param action  触发动作
     * @param data    产生数据
     * @param ip      产生IP
     * @param userId      产生人
     */
    void addLog(String action, String data, String ip, Long userId);
}
