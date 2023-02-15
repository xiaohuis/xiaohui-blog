package com.xiaohuis.filter;

import com.xiaohuis.constant.Message;
import com.xiaohuis.constant.SystemConstant;
import com.xiaohuis.pojo.LoginUser;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.util.CommonUtil;
import com.xiaohuis.util.JwtUtil;
import com.xiaohuis.util.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = CommonUtil.getCookieToken(httpServletRequest);
        // 请求URL不包含域名
        String uri = httpServletRequest.getRequestURI();
        if (!StringUtils.hasText(token)){
            if(uri.startsWith("/admin") && !uri.startsWith("/admin/login")){
                httpServletResponse.sendRedirect("http://localhost/admin/login");
                return;
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String id;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new BusinessException(Code.BUSINESS_ERR, Message.TOKEN_INFORMATION_ERR_MSG);
            throw new RuntimeException(Message.TOKEN_INFORMATION_ERR_MSG);
        }
        String redisKey = SystemConstant.LOGIN_REDIS_KEY +id;
        LoginUser loginUser = redisCache.get(redisKey, LoginUser.class);
        if (Objects.isNull(loginUser))  {
//            throw new BusinessException(Code.BUSINESS_ERR, Message.LOGIN_INFORMATION_ERR_MSG);
//            throw new RuntimeException(Message.LOGIN_INFORMATION_ERR_MSG);
            httpServletResponse.sendRedirect("http://localhost/admin/login");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Long expire = redisCache.getExpire(SystemConstant.LOGIN_REDIS_KEY + loginUser.getUser().getId(), TimeUnit.MINUTES);
        if(expire <= 30){
            redisCache.expire(SystemConstant.LOGIN_REDIS_KEY+id, 30L, TimeUnit.MINUTES);
        }else {
            redisCache.expire(SystemConstant.LOGIN_REDIS_KEY+id, 7L, TimeUnit.DAYS);
        }
        UserVo userVo = new UserVo(loginUser.getUser());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SystemConstant.LOGIN_SESSION_KEY, userVo);
        session.setMaxInactiveInterval(30*60);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


}
