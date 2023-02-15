package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Files;

/**
 * <p> </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/10/17 17:56
 */
public interface FilesService {

    /**
     * 查询文件，并分页显示
     * @param pageNum
     * @return
     */
    PageInfo<Files> findAllFilesByPage(Integer pageNum);

    /**
     * 保存文件
     * @param files1
     */
    void saveFiles(Files files1);

    /**
     * 根据fileId删除file
     * @param id
     * @return
     */
    void deleteFilesById(Long id);

    /**
     * 查询file总数
     * @return
     */
    int findFileCount();
}
