package com.xiaohuis.dao;

import com.xiaohuis.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohui
 * @since 2022/10/17
 */

@Mapper
public interface FilesDao {

    /**
     * 查询所有文件
     * @return
     */
    List<Files> findAllFile();

    /**
     * 保存file
     * @param files
     */
    void saveFiles(@Param("files") Files files);

    /**
     * 根据id更新file的状态为0，实现逻辑删除
     * @param id
     */
    void updateFileStatusById(@Param("id") Long id);

    /**
     * 查询file总数
     * @return
     */
    int findFileCount();
}
