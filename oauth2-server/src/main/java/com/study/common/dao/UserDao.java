package com.study.common.dao;

import com.study.common.BaseDao;
import com.study.model.User;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 注解方式完成映射
 *
 * @author xujiping 2018-01-16 17:37
 */

public interface UserDao extends BaseDao<User>{

    @Select("select * from user where username=#{username}")
    public User selectUserByName(String username);

    @Update("update user set password=#{newPassword} where id=#{userId}")
    public int changePassword(Long userId, String newPassword);

}
