package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.FilesDao;
import com.xiaohuis.entity.Files;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/10/17 17:56
 */

@Transactional
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesDao filesDao;

    @Override
    public PageInfo<Files> findAllFilesByPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum,10);
        //获取分页相关数据
        PageInfo<Files> filePageInfo = new PageInfo<>(filesDao.findAllFile());
        return filePageInfo;
    }


    @Override
    public void saveFiles(Files files1) {
        if(files1 == null){
            throw  new BusinessException(Code.UPLOAD_FILE_ERR_CODE, Message.UPLOAD_FILE_ERR_MSG);
        }
        System.out.println(files1);
        filesDao.saveFiles(files1);
    }

    @Override
    public void deleteFilesById(Long id) {
        if(id == null){
            throw  new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        filesDao.updateFileStatusById(id);
    }

    @Override
    public int findFileCount() {
        return filesDao.findFileCount();
    }
}
