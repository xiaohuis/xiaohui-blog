package com.xiaohuis.service;

import com.xiaohuis.pojo.LoginUserVo;
import com.xiaohuis.pojo.PasswordVo;
import com.xiaohuis.pojo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
public interface UserService {

    /**
     * 根据user中的username查询user
     * @param loginUserVo
     * @param httpServletResponse
     * @return
     */
    UserVo login(LoginUserVo loginUserVo, HttpServletResponse httpServletResponse);

    /**
     * 更新个人信息
     * @param userVo
     * @param httpServletResponse
     * @param session
     */
    void updateUserById(UserVo userVo, HttpServletResponse httpServletResponse, HttpSession session);

    /**
     * 更新用户密码
     * @param passwordVo
     * @param httpServletRequest
     * @return
     */
    void updateUserPasswordById(PasswordVo passwordVo, HttpServletRequest httpServletRequest);

    /**
     * 退出登录
     * @param httpServletResponse
     * @param httpSession
     * @return
     */
    String loginOut(HttpServletResponse httpServletResponse, HttpSession httpSession);
}
