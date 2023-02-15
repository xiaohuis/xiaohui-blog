package com.xiaohuis.service.impl;

import com.xiaohuis.entity.AllLog;
import com.xiaohuis.service.AllLogService;
import com.xiaohuis.dao.AllLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AllLogServiceImpl implements AllLogService {

    @Autowired
    private AllLogDao AllLogDao;

    @Override
    public void addAllLog(AllLog allLog) {

        if(null != allLog){
            AllLogDao.saveAllLog(allLog);
        }

    }
}
