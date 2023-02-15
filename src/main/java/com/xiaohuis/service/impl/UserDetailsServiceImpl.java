package com.xiaohuis.service.impl;

import com.xiaohuis.constant.Message;
import com.xiaohuis.dao.MenuDao;
import com.xiaohuis.dao.UserDao;
import com.xiaohuis.entity.User;
import com.xiaohuis.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>  </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/10 0:55
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        //如果没有查询到用户就抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException(Message.Login_INFORMATION_ERR_MSG);
        }

        List<String> list = menuDao.findPermsByUserId(user.getId());
        return new LoginUser(user,list);
    }
}
