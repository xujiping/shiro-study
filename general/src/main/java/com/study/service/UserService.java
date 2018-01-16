package com.study.service;
/**
* @author xujiping
* @version 2017年4月26日 下午5:09:23
* 类说明
*/

import com.study.model.User;

import java.util.Map;

public interface UserService {
	
	/**
	 * 根据条件查询用户信息
	 * @param map
	 * @return
	 */
	public User getUser(Map<String, Object> map);
	
	/**
	 * 通过Id，更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	
	/**
	 * 通过用户名查询用户信息
	 * @param username
	 * @return
	 */
	public User getUserByName(String username);
	
}
