package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.dao.LinkDao;
import com.xiaohuis.entity.Link;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.service.LinkService;
import com.xiaohuis.util.RedisCache;
import org.apache.commons.lang3.StringUtils;
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
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public int findLinkCount() {
        return linkDao.findLinkCount();
    }

    @Override
    public PageInfo<Link> findAllLinkAndPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum,10);
        //获取分页相关数据
        PageInfo<Link> linkPageInfo = new PageInfo<>(linkDao.findAllLink());
        return linkPageInfo;
    }

    @Override
    public void saveLink(Link link) {
        if(link == null || StringUtils.isBlank(link.getLink()) || StringUtils.isBlank(link.getNickname())){
            throw new BusinessException(Code.ADD_ERR_CODE, Message.ADD_ERR_MSG);
        }
        link.setCreateTime(new Date());
        linkDao.saveLink(link);
        redisCache.delete(OtherCommonConstants.LINKS_REDIS_KEY);
    }

    @Override
    public void updateLink(Link link) {

        if(link == null || StringUtils.isBlank(link.getLink()) || StringUtils.isBlank(link.getNickname())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.UPDATE_ERR_MSG);
        }
        linkDao.updateLink(link);
        redisCache.delete(OtherCommonConstants.LINKS_REDIS_KEY);
    }

    @Override
    public void deleteLinkById(Long id) {
        if(id == null){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        linkDao.updateLinkStatusById(id);
        redisCache.delete(OtherCommonConstants.LINKS_REDIS_KEY);
    }
}
