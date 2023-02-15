package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.LogsDao;
import com.xiaohuis.entity.Logs;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Service
@Transactional
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsDao logsDao;

    @Override
    public PageInfo<Logs> findLogsByPaging(int pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum,5);
        //获取分页相关数据
        PageInfo<Logs> logs = new PageInfo<>(logsDao.findLogs());
        return logs;
    }

    @Override
    public void addLog(String action, String data, String ip, Long userId) {
        Logs logs = new Logs();
        if(action == null || data == null || ip == null || userId == null ){
            throw new BusinessException(Code.BUSINESS_ERR, Message.INFORMATION_NULL_ERR_MSG);
        }
        logs.setAction(action);
        logs.setData(data);
        logs.setIp(ip);
        logs.setUserId(userId);
        logs.setCreatedTime(new Date());

        logsDao.saveLogs(logs);
    }
}
