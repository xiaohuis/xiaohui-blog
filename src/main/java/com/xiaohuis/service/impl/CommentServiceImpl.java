package com.xiaohuis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.CommentDao;
import com.xiaohuis.entity.Comment;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.pojo.PublishedComment;
import com.xiaohuis.service.CommentService;
import com.xiaohuis.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public Map<String, List<Comment>> findCommentsByBlogId(Long blogId) {
        Map<String, List<Comment>> commentMap=new HashMap<>(2);
        commentMap.put("parents",commentDao.findParentsCommentByBlogId(blogId));
        commentMap.put("sons",commentDao.findSonsCommentByBlogId(blogId));
        return commentMap;
    }

    @Override
    public void insertComment(Comment comment) {
        if (comment == null || StringUtils.isBlank(comment.getName())
                || StringUtils.isBlank(comment.getContent()) || StringUtils.isBlank(comment.getEmail())){
            throw  new BusinessException(Code.ADD_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }
        if (!CommonUtil.isEmail(comment.getEmail())){
            throw new BusinessException(Code.ADD_ERR_CODE, Message.EMAIL_FORMAT_ERR_MSG);
        }
        comment.setCreatTime(new Date());
        comment.setStatus(1);
        commentDao.insertComment(comment);

    }

    @Override
    public PageInfo<Comment> findCommentsByPaging(int pageNum) {

        PageHelper.startPage(pageNum,5);
        List<Comment> commentList  = commentDao.findCommentByConditions();
        PageInfo<Comment> comments = new PageInfo<>(commentList);
        return comments;
    }

    @Override
    public PageInfo<PublishedComment> findCommentsAndBlogTitleByPaging(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<PublishedComment> publishedCommentList = commentDao.findCommentsAndBlogTitle();
        PageInfo<PublishedComment> commentDto = new PageInfo<>(publishedCommentList);
        return commentDto;
    }

    @Override
    public void updateCommentStaticById(Long id) {
        if(id == null){
            throw  new BusinessException(Code.UPDATE_ERR_CODE, Message.UPDATE_ERR_MSG);
        }
        commentDao.updateCommentStaticById(id,1);
    }

    @Override
    public void deleteCommentById(Long id) {
        if(id == null){
            throw  new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        commentDao.updateCommentStaticById(id,0);
    }

    @Override
    public void deleteCommentByBlogId(Long blogId) {
        if(blogId == null){
            throw  new BusinessException(Code.DELETE_ERR_CODE, Message.DELETE_ERR_MSG);
        }
        commentDao.updateCommentStaticByBlogId(blogId);
    }
}
