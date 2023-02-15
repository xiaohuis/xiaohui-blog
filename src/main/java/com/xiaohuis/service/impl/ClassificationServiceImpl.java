package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.ClassificationDao;
import com.xiaohuis.entity.Classification;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.pojo.BlogVo;
import com.xiaohuis.service.ClassificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public PageInfo<Classification> findClassificationByPaging() {
        //开启分页功能
        PageHelper.startPage(1,10);
        //获取分页相关数据
        PageInfo<Classification> classifications = new PageInfo<>(classificationDao.findAllClassification());
        return classifications;
    }

    @Override
    public List<Classification> findAllClassification() {
        return classificationDao.findAllClassification();
    }

    @Override
    public void updateClassificationBlogNumber(BlogVo blogVo) {
        Classification classification1 = classificationDao.findClassificationById(blogVo.getClassificationId());
        Classification classification2 = null;
        if(blogVo.getOldClassificationId() != null){
            classification2 = classificationDao.findClassificationById(blogVo.getOldClassificationId());

        }
        if (blogVo.getOldClassificationId() != null  && !blogVo.getClassificationId().equals(blogVo.getOldClassificationId()) && classification2.getBlogNumber() != 0){
            classification2.setBlogNumber(classification2.getBlogNumber() - 1);
            classificationDao.updateClassificationBlogNumberByClassificationId(classification2);
        }
        classification1.setBlogNumber(classification1.getBlogNumber() + 1);
        classificationDao.updateClassificationBlogNumberByClassificationId(classification1);
    }

    @Override
    public void updateClassificationBlogNumber(Long classificationId) {
        Classification classification = classificationDao.findClassificationById(classificationId);
        if(classification.getBlogNumber() != 0){
            classification.setUpdateTime(new Date());
            classification.setBlogNumber(classification.getBlogNumber() - 1);
            classificationDao.updateClassificationBlogNumberByClassificationId(classification);
        }
    }

    @Override
    public void updateClassification(Classification classification) {

        if(classification == null || classification.getId() == null || StringUtils.isBlank(classification.getClassificationName()) || StringUtils.isBlank(classification.getIntroduction())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }
        classification.setUpdateTime(new Date());
        classificationDao.updateClassificationByClassificationId(classification);
    }

    @Override
    public void saveClassification(Classification classification) {
        if(classification == null || StringUtils.isBlank(classification.getClassificationName()) || StringUtils.isBlank(classification.getIntroduction())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }
        classification.setCreatTime(new Date());
        classificationDao.saveClassification(classification);
    }

    @Override
    public void deleteClassificationByClassificationId(Long classificationId) {
        if(classificationId == null){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        if(classificationDao.findClassificationById(classificationId).getBlogNumber() != 0){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        classificationDao.deleteClassificationByClassificationId(classificationId);
    }


}
