package com.xiaohuis.dao;

import com.xiaohuis.entity.User;
import com.xiaohuis.pojo.PasswordVo;
import com.xiaohuis.pojo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

@Mapper
public interface UserDao{

    /**
     * 根据单个主键查询User
     * @param id
     * @return
     */
    User findUserById(@Param("id") Long id);

    /**
     * 根据username查询user
     * @param username
     * @return
     */
    User findUserByUsername(@Param("username") String username);

    /**
     * 根据id更新user信息
     * @param userVo
     */
    void updateUserById(@Param("userVo") UserVo userVo);

    /**
     * 根据id更新user密码
     * @param userPasswordVo
     */
    void updateUserPasswordById(@Param("userPasswordVo") PasswordVo userPasswordVo);
}
