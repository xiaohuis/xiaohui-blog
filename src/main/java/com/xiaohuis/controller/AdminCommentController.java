package com.xiaohuis.controller;


import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.LogActions;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.pojo.PublishedComment;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.service.CommentService;
import com.xiaohuis.service.LogsService;
import com.xiaohuis.util.FrontEndCommonsUtil;
import com.xiaohuis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  管理员评论管理前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin/comments")
public class AdminCommentController {

    @Autowired
    private FrontEndCommonsUtil commons;


    @Autowired
    private CommentService commentService;

    @Autowired
    private LogsService logsService;

    /**
     * 查询所有评论，前往评论列表页面
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("")
    public String findAllCommentAndPage(
            @RequestParam(name = "page", required = false, defaultValue = "1")
            Integer pageNum,
            Model model){
        PageInfo<PublishedComment> CommentByPaging = commentService.findCommentsAndBlogTitleByPaging(pageNum);
        model.addAttribute("comments",CommentByPaging);
        model.addAttribute("commons",commons);
        return "admin/comment_list";
    }

    /**
     * 审核评论
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public Result updateCommentStatic(
            @PathVariable
            Long id
            ){
        commentService.updateCommentStaticById(id);
        return new Result(Code.UPDATE_SUCCESS_CODE, Message.UPDATE_SUCCESS_MSG);
    }

    /**
     * 删除评论
     * @param id
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:comments:delete')")
    public Result deleteCommentById(
            @PathVariable
            Long id,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest
    ){

        commentService.deleteCommentById(id);
        UserVo userVo = (UserVo) httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY);
        Long userId = userVo.getId();
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_COMMENT, id + "", httpServletRequest.getRemoteAddr(), userId);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }


}

