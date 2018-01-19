package com.study.service;
/**
 * @author xujiping
 * @version 2017年4月26日 下午5:09:23 类说明
 */

import com.study.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public int add(User user);

    public int update(User user);

    public void delete(Long id);

    /**
     * 修改用户密码
     */
    public void changePassword(Long id, String newPassword);

    User findOne(Long id);

    List<User> findAll();

    /**
     * 根据条件查询用户信息
     */
    public User getUser(Map<String, Object> map);

    /**
     * 通过用户名查询用户信息
     */
    public User getUserByName(String username);

}
