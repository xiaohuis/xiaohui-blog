package com.xiaohuis.controller;


import com.alibaba.fastjson.JSON;
import com.xiaohuis.constant.*;
import com.xiaohuis.pojo.PasswordVo;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.service.*;
import com.xiaohuis.util.CheckCodeUtil;
import com.xiaohuis.util.RedisCache;
import com.xiaohuis.util.Result;
import com.xiaohuis.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  管理员首页前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private LogsService logsService;

    @Autowired
    private UserService userService;

    @Autowired
    private FilesService filesService;

    @Autowired
    private RedisCache redisCache;


    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String adminIndex(Model model){
        model.addAttribute("comments",commentService.findCommentsByPaging(1));
        model.addAttribute("blogs",blogService.findBlogByRecommendAndPaging(1,2));
        model.addAttribute("linkCount",linkService.findLinkCount());
        model.addAttribute("fileCount",filesService.findFileCount());
        model.addAttribute("logs",logsService.findLogsByPaging(1));
        return "admin/index";
    }

    /**
     * 前往个人信息管理页面
     * @return
     */
    @GetMapping("/personal-information")
    public String personalInformation(){
        return "admin/profile";
    }

    /**
     * 更新个人信息
     * @param userVo
     * @param httpServletRequest
     * @return
     */
    @PutMapping("/personal-information/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:user:update')")
    public Result updatePersonalInformation(
            UserVo userVo,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        HttpSession session = httpServletRequest.getSession();
        userService.updateUserById(userVo, httpServletResponse, session);
        logsService.addLog(LogActions.LOGS_ACTION_UPDATE_USER, JSON.toJSONString(userVo),httpServletRequest.getRemoteAddr(), userVo.getId());
        return new Result(Code.UPDATE_SUCCESS_CODE, Message.UPDATE_SUCCESS_MSG);
    }


    /**
     * 获取图片验证码
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/CheckCodeServlet")
    @ResponseBody
    public String CheckCodeServlet(
            HttpServletResponse response,
            HttpServletRequest httpServletRequest
    ) throws IOException {
        String ip = httpServletRequest.getRemoteAddr();
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(95, 30, os, 4);
        redisCache.set(OtherCommonConstants.CHECK_CODE_KEY + ip,checkCode,10L, TimeUnit.MINUTES);
        return checkCode;
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @PostMapping("/send-email-code")
    @ResponseBody
    public Result sendEmailCode(
            @RequestParam(name = "email", required = true)
            String email,
            HttpSession httpSession
    ) {
        UserVo userVo = (UserVo) httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY);
        String emails = userVo.getEmail();
        if(!emails.equals(email)){
            return new Result(Code.EMAIL_ERR_CODE, Message.EMAIL_INFORMATION_ERR_MSG);
        }
        //生成邮箱验证码
        String emailCode = CheckCodeUtil.generateVerifyCode(4, SystemConstant.VERIFY_CODES);
        redisCache.set(OtherCommonConstants.EMAIL_CODE_KEY + email, emailCode,5L, TimeUnit.MINUTES);
        //发送邮箱验证码
        SendMailUtil sendMailUtil = new SendMailUtil();
        sendMailUtil.sendMail(email, OtherCommonConstants.MAIL_THEME_PWD, OtherCommonConstants.MAIL_CONTENT1+emailCode+OtherCommonConstants.MAIL_CONTENT2);
        return new Result(Code.EMAIL_SUCCESS_CODE, Message.SEND_EMAIL_SUCCESS_MSG);
    }

    /**
     * 修改密码
     * @param passwordVo
     * @param httpServletRequest
     * @return
     */
    @PutMapping("/password")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:password:update')")
    public Result updateUserPassword(
            PasswordVo passwordVo,
            HttpServletRequest httpServletRequest
    ){
        userService.updateUserPasswordById(passwordVo, httpServletRequest);
        logsService.addLog(LogActions.LOGS_ACTION_PASSWORD, JSON.toJSONString(passwordVo),httpServletRequest.getRemoteAddr(), passwordVo.getId());
        return new Result(Code.UPDATE_SUCCESS_CODE, Message.UPDATE_SUCCESS_MSG);
    }

}

