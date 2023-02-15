package com.xiaohuis.controller;


import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.entity.Comment;
import com.xiaohuis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前台评论 前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     * @param comment 评论实体类
     * @param httpSession
     * @return
     */
    @PostMapping("/comment")
    public String insertComment(
            Comment comment,
            HttpSession httpSession){

        //判断是否为博主
        if(httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY) != null){
            comment.setIsAdmin(1);
        }else {
            comment.setIsAdmin(0);
        }
        commentService.insertComment(comment);
        return "redirect:/blogDetails/"+comment.getBlogId();
    }

}

