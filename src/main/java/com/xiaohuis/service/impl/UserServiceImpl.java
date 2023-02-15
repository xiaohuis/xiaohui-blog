package com.xiaohuis.service.impl;

import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.dao.UserDao;
import com.xiaohuis.entity.User;
import com.xiaohuis.exception.BusinessException;
import com.xiaohuis.pojo.*;
import com.xiaohuis.service.UserService;
import com.xiaohuis.util.CommonUtil;
import com.xiaohuis.util.JwtUtil;
import com.xiaohuis.util.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserVo login(LoginUserVo loginUserVo, HttpServletResponse httpServletResponse) {

        //判空
        if(null == loginUserVo || StringUtils.isBlank(loginUserVo.getUsername()) || StringUtils.isBlank(loginUserVo.getPassword())){
            throw new BusinessException(Code.LOGIN_ERR_CODE, Message.Login_INFORMATION_NULL_ERR_MSG);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserVo.getUsername(), loginUserVo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new BusinessException(Code.LOGIN_ERR_CODE, Message.Login_INFORMATION_ERR_MSG);
        }
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        Long id = principal.getUser().getId();
        String jwt = JwtUtil.createJWT(String.valueOf(id));
        redisCache.set(SystemConstant.LOGIN_REDIS_KEY+id,principal,30L, TimeUnit.MINUTES);
        Cookie cookie = new Cookie(SystemConstant.USER_LOGIN_COOKIE, jwt);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        if(StringUtils.isNotBlank(loginUserVo.getRememberMe())){
            redisCache.expire(SystemConstant.LOGIN_REDIS_KEY+id, 7, TimeUnit.DAYS);
        }else {
            redisCache.expire(SystemConstant.LOGIN_REDIS_KEY+id, 30L, TimeUnit.MINUTES);
        }
        UserVo userVo = new UserVo(principal.getUser());
        return userVo;
    }

    @Override
    public void updateUserById(UserVo userVo, HttpServletResponse httpServletResponse, HttpSession session) {
        //判空
        if(null == userVo || StringUtils.isBlank(userVo.getNickname()) || StringUtils.isBlank(userVo.getEmail())
                || StringUtils.isBlank(userVo.getIcon()) || StringUtils.isBlank(userVo.getPhone())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }
        //格式验证
        if (userVo.getPhone().length() > OtherCommonConstants.MAX_PHONE_LENGTH){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.MAX_PHONE_LENGTH_ERR_MSG);
        }
        if (!CommonUtil.isEmail(userVo.getEmail())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.EMAIL_FORMAT_ERR_MSG);
        }

        userDao.updateUserById(userVo);
        User user = userDao.findUserById(userVo.getId());
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        loginUser.setUser(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        LoginUser principal = (LoginUser) auth.getPrincipal();
        Long expire = redisCache.getExpire(SystemConstant.LOGIN_REDIS_KEY + principal.getUser().getId(), TimeUnit.MINUTES);
        if(expire <= 30){
            redisCache.set(SystemConstant.LOGIN_REDIS_KEY+principal.getUser().getId(),principal,30L, TimeUnit.MINUTES);
        }else {
            redisCache.set(SystemConstant.LOGIN_REDIS_KEY+principal.getUser().getId(),principal,7L, TimeUnit.DAYS);
        }
        session.setAttribute(SystemConstant.LOGIN_SESSION_KEY, userVo);
        session.setMaxInactiveInterval(30*60);
    }

    @Override
    public void updateUserPasswordById(PasswordVo passwordVo, HttpServletRequest httpServletRequest) {

        //验证信息是否为空
        if(passwordVo == null || passwordVo.getId() == null || StringUtils.isBlank(passwordVo.getEmail()) || StringUtils.isBlank(passwordVo.getNewPassword())
                || StringUtils.isBlank(passwordVo.getRepassPassword()) || StringUtils.isBlank(passwordVo.getVerificationCode())
                || StringUtils.isBlank(passwordVo.getUserEmailCode())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.INFORMATION_NULL_ERR_MSG);
        }

        //验证两次密码是否一致
        if(!passwordVo.getNewPassword().equals(passwordVo.getRepassPassword())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.PASSWORD_ERR_MSG);
        }
        //获取存储在redis的验证码
        String ip = httpServletRequest.getRemoteAddr();

        passwordVo.setCheckCode(redisCache.get(OtherCommonConstants.CHECK_CODE_KEY + ip, String.class));
        passwordVo.setEmailCode(redisCache.get(OtherCommonConstants.EMAIL_CODE_KEY + passwordVo.getEmail(), String.class));
        System.out.println(passwordVo);
        //验证验证码是否一致
        if(!passwordVo.getCheckCode().equalsIgnoreCase(passwordVo.getVerificationCode())
                || !passwordVo.getEmailCode().equals(passwordVo.getUserEmailCode())){
            throw new BusinessException(Code.UPDATE_ERR_CODE, Message.VERIFICATION_EMAIL_CODE_ERR_MSG);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode(passwordVo.getNewPassword());
        passwordVo.setNewPassword(pwd);
        userDao.updateUserPasswordById(passwordVo);
    }

    @Override
    public String loginOut(HttpServletResponse httpServletResponse, HttpSession httpSession) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long id = loginUser.getUser().getId();
        Long expire = redisCache.getExpire(SystemConstant.LOGIN_REDIS_KEY + id, TimeUnit.MINUTES);
        if(expire <= 30){
            redisCache.delete(SystemConstant.LOGIN_REDIS_KEY + id);
            Cookie cookie = new Cookie(SystemConstant.USER_LOGIN_COOKIE, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
            httpSession.invalidate();
        }
        return "redirect:/admin/login";
    }
}
