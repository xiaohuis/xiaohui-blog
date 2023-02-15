package com.xiaohuis.service;

import com.xiaohuis.entity.AllLog;

/**
 * <p>
 *  添台日志服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface AllLogService {

    /**
     * 添加前台日志
     * @param allLog
     */
    void addAllLog(AllLog allLog);

}
