package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.TagDao;
import com.xiaohuis.entity.Tag;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.pojo.BlogVo;
import com.xiaohuis.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public PageInfo<Tag> findTagByPage() {
        //开启分页功能
        PageHelper.startPage(1,10);
        //获取分页相关数据
        PageInfo<Tag> tags = new PageInfo<>(tagDao.findAllTag());
        return tags;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagDao.findAllTag();
    }

    @Override
    public void addAndUpdateTags(BlogVo blogVo) {

        Tag tag = new Tag();
        tag.setCreatTime(blogVo.getCreatTime() != null ? blogVo.getCreatTime() : new Date());
        List<String> tags2 = null;

        //前端获取的tags为一串字符串，需用split分割，并循环逐个保存
        if (StringUtils.isBlank(blogVo.getTags())) {
            throw new BusinessException(Code.BUSINESS_ERR,Message.PARAM_IS_EMPTY);
        }

        List<String> tags = Arrays.asList(StringUtils.split(blogVo.getTags(), ","));

        for (String tagName : tags) {
            //新增tags前，先查找tag是否存在
            Tag newTag = tagDao.findTagByTagName(tagName);

            //前端获取的oldTags为一串字符串，需用split分割，并循环逐个与新tags比对，如新旧相同，则不做操作，如不相同，则删除旧标签与博客的对应联系，减少对应的博客数量
            if (StringUtils.isNotBlank(blogVo.getOldTags())) {

                tags2 = Arrays.asList(StringUtils.split(blogVo.getOldTags(), ","));

                //如果新旧标签一样，则无需修改，跳出上面的for的本次循环，执行下次循环
                if (tags2.contains(tagName)) {
                    continue;
                }
            }

            //如果无旧标签或新旧标签不相同，如果新tag不存在,则新增并返回自增id，存在，则更新博客数量和更新时间
            if (null == newTag || "".equals(newTag) || "null".equals(newTag)) {
                tag.setTagName(tagName);
                tag.setBlogNumber(1L);
                tagDao.addTags(tag);
            } else {
                newTag.setBlogNumber(newTag.getBlogNumber() + 1);
                newTag.setUpdateTime(blogVo.getUpdateTime() != null ? blogVo.getUpdateTime() : new Date());
                tagDao.updateTagByTagId(newTag);
                tag.setId(newTag.getId());
            }
            //查询tag和blog的对应关系的id,如不存在，则新增tag和blog的对应关系
            if (null == tagDao.findTagAndBlogByTagIdAndBlogId(blogVo.getId(), tag.getId())) {
                tagDao.addTagAndBlog(blogVo.getId(), tag.getId());
            }
        }

        if (tags2 != null &&!tags2.isEmpty()){
            for (String tagName2 : tags2) {
                if (!tags.contains(tagName2)) {
                    //查找旧tag是否存在
                    Tag oldTag = tagDao.findTagByTagName(tagName2);
                    //如不存在或者对应的BlogNumber为0，则不需要减少博客数量
                    if (null == oldTag || oldTag.getBlogNumber() == 0) {
                        continue;
                    }
                    oldTag.setBlogNumber(oldTag.getBlogNumber() - 1);
                    oldTag.setUpdateTime(blogVo.getUpdateTime());
                    tagDao.updateTagByTagId(oldTag);
                    tagDao.deleteTagAndBlog(blogVo.getId(), oldTag.getId());
                }
            }
        }
    }

    @Override
    public void updateTagsBlogNumber(List<Tag> tagList) {
        for(Tag tag : tagList){
            Tag tag1 = tagDao.findTagByTagName(tag.getTagName());
            if (tag1.getBlogNumber() != 0){
                tag1.setBlogNumber(tag1.getBlogNumber() - 1);
                tag1.setUpdateTime(new Date());
                tagDao.updateTagByTagId(tag1);
            }
        }
    }

    @Override
    public void deleteTagByTagId(Long tagId) {
        if(null == tagId){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }

        if(tagDao.findTagByTagId(tagId).getBlogNumber() != 0){
            throw new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }

        tagDao.deleteTagByTagId(tagId);
    }

    @Override
    public void updateTagsBlogNumberAndRelation(String oldTags, Long blogId) {
        List<String> tags2 = Arrays.asList(StringUtils.split(oldTags, ","));
        for(String tagName : tags2){
            Tag tag1 = tagDao.findTagByTagName(tagName);
            if (tag1.getBlogNumber() != 0){
                tag1.setBlogNumber(tag1.getBlogNumber() - 1);
                tag1.setUpdateTime(new Date());
                tagDao.updateTagByTagId(tag1);
            }
            tagDao.deleteTagAndBlog(blogId, tag1.getId());
        }
    }
}
