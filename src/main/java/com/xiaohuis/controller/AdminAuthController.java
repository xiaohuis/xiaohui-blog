package com.xiaohuis.controller;


import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.LogActions;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.pojo.LoginUserVo;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.service.LogsService;
import com.xiaohuis.service.UserService;
import com.xiaohuis.util.FrontEndCommonsUtil;
import com.xiaohuis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  登录验证前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private FrontEndCommonsUtil frontEndCommonsUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private LogsService logsService;


    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("frontEndCommonsUtil",frontEndCommonsUtil);
        return "admin/login";
    }

    /**
     * 管理员填写信息进行登录
     * @param
     * @return-
     */
    @PostMapping("/login")
    @ResponseBody
    public Result adminLogin(LoginUserVo loginUserVo,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse
    ){
        UserVo userVo = userService.login(loginUserVo, httpServletResponse);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SystemConstant.LOGIN_SESSION_KEY, userVo);
        session.setMaxInactiveInterval(30*60);
        logsService.addLog(LogActions.LOGS_ACTION_LOGIN, userVo.getUsername() + "用户", httpServletRequest.getRemoteAddr(), userVo.getId());
        return new Result(Code.LOGIN_SUCCESS_CODE, Message.Login_SUCCESS_MSG);
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/login-out")
    public String adminLoginOut(
            HttpSession httpSession,
            HttpServletResponse httpServletResponse
    ){
        return userService.loginOut(httpServletResponse, httpSession);
    }

}

