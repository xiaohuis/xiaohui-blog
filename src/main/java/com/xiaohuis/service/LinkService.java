package com.xiaohuis.service;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.entity.Link;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface LinkService {

    /**
     * 查询链接总数
     * @return
     */
    int findLinkCount();

    /**
     * 查询链接，并分页显示
     * @param pageNum
     * @return
     */
    PageInfo<Link> findAllLinkAndPage(Integer pageNum);

    /**
     * 保存链接
     * @param link
     * @return
     */
    void saveLink(Link link);

    /**
     * 更新链接
     * @param link
     * @return
     */
    void updateLink(Link link);

    /**
     * 根据id删除链接
     * @param id
     * @return
     */
    void deleteLinkById(Long id);
}
