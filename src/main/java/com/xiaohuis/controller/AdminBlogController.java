package com.xiaohuis.controller;


import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.LogActions;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.pojo.BlogVo;
import com.xiaohuis.pojo.FindBlogVo;
import com.xiaohuis.pojo.LoginUser;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.service.BlogService;
import com.xiaohuis.service.ClassificationService;
import com.xiaohuis.service.LogsService;
import com.xiaohuis.util.FrontEndCommonsUtil;
import com.xiaohuis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  管理员博客管理前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private FrontEndCommonsUtil frontEndCommonsUtil;


    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LogsService logsService;

    /**
     * 查询所有博客，前往博客列表页面
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("")
    public String findAllBlogAndPage(
            @RequestParam(name = "page", required = false, defaultValue = "1")
            Integer pageNum,
            Model model){
        PageInfo<FindBlogVo> searchBlog = blogService.searchBlogByConditionsAndPaging(null,pageNum,3);
        model.addAttribute("blogs",searchBlog);
        model.addAttribute("frontEndCommonsUtil",frontEndCommonsUtil);
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        UserVo userVo = new UserVo(loginUser.getUser());
        model.addAttribute("userVo",userVo);
        return "admin/blog_list";
    }


    /**
     * 前往博客发表页面
     * @param model
     * @return
     */
    @GetMapping("/publish")
    public String newBlog(Model model){
        model.addAttribute("classifications",classificationService.findAllClassification());
        return "admin/editor_blog";
    }

    /**
     * 根据id查看博客详情，前往修改博客页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String modifyBlog(
            @PathVariable
            Long id,
            Model model){
        model.addAttribute("blog",blogService.findBlogCombinationByBlogId(id));
        model.addAttribute("classifications",classificationService.findAllClassification());
        model.addAttribute("active", "article");
        return "admin/editor_blog";
    }

    /**
     * 修改博客
     * @param blogVo
     * @return
     */
    @PutMapping("/modify")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:blog:update')")
    public Result modifyBlog(
            BlogVo blogVo
            ){
        blogService.updateBlog(blogVo);
        return new Result(Code.ADD_SUCCESS_CODE, Message.ADD_SUCCESS_MSG);
    }

    /**
     * 发表博客
     * @param blogVo
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:blog:add')")
    public Result publishBlog(
            BlogVo blogVo
    ){
        blogService.addBlog(blogVo);
        return new Result(Code.ADD_SUCCESS_CODE, Message.ADD_SUCCESS_MSG);
    }

    /**
     * 删除博客
     * @param blogId
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/delete/{blogId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:blog:delete')")
    public Result deleteBlog(
            @PathVariable
            Long blogId,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest
    ){
        blogService.deleteBlog(blogId);
        UserVo userVo = (UserVo) httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY);
        Long userId = userVo.getId();
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_BLOG, blogId + "", httpServletRequest.getRemoteAddr(), userId);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }


}

